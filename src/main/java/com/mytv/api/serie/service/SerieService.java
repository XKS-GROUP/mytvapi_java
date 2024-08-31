package com.mytv.api.serie.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.intervenant.repository.DirectorRepository;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.repository.LangRepository;
import com.mytv.api.ressource.service.GenreService;
import com.mytv.api.ressource.service.SerieGenre;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.repository.FavSerieRepository;
import com.mytv.api.serie.repository.GenreRepository;
import com.mytv.api.serie.repository.SerieGenreRepository;
import com.mytv.api.serie.repository.SerieRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class SerieService {


	@Autowired
	private SerieRepository rep;
	
	@Autowired
	GenreRepository genreRep;
	
	@Autowired
	SerieGenreRepository serieGenreRep;
	
	@Autowired
	FavSerieRepository rep_fav_serie;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	private ActorRepository rep_actor;
	
	@Autowired
	private DirectorRepository rep_dirs;
	
	@Autowired
	private LangRepository rep_langue;

	public Serie create(Serie g) {
		
		g.setActeurs(rep_actor.findAllById(g.getActeurList()));
		g.setGenres(genreRep.findAllById(g.getGenreList()));
		g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
		g.setList_langues(rep_langue.findAllById(g.getLangue()));
		//Recuperation de la liste des genres
		//Teste de chaque valeur, si il n existe pas ce genre sera creer
		Serie serie = rep.save(g);

		if (!g.getGenreList().isEmpty()){

			for (Long gr : g.getGenreList()) {
				
					addSerieGenreId(g.getIdSerie(), gr);

			 }
				
		}

		return serie;

	}
	
	public Page<Serie> similaire_show(Long id, Pageable p) {
		
		refresh();
		Serie m =  rep.findById(id).get();
		
		PageImpl<Serie> res = new PageImpl<Serie>(rep.findAll().stream()
				   .filter(rd -> rd.getGenreList().containsAll(m.getGenreList()))
				   .toList() 
				   , p
				   , rep.findAll().size());
		return res;
	}

	public void refresh() {
		
		rep_fav_serie.findAll().forEach(
				
				f -> {
					
					f.getSerie().setFavorie(true);
					
				}
				
			);
		
		
	       List<Serie> l = rep.findAll();
			
			l.forEach(  
					
					g -> {
						g.setActeurs(rep_actor.findAllById(g.getActeurList()));
						g.setGenres(genreRep.findAllById(g.getGenreList()));
						g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
						g.setList_langues(rep_langue.findAllById(g.getLangue()));
					}
			);
		}
	
	public List<Serie> show() {

		refresh();
		return rep.findAll();
	}
	
	public Page<Serie> showPage(Pageable p) {
		refresh();
		return rep.findAll(p);
	}
	
	public Page<Serie> showByLangue(Long id, Pageable p){
		refresh();
		
		PageImpl<Serie> res = new PageImpl<Serie>(
				rep.findAll().stream()
                .filter(f -> f.getLangue().contains(id))
                .filter(f -> f.isStatus() == true)
                .collect(Collectors.toList()) 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Serie> showByGenre(Long id, Pageable p){
		refresh();
		PageImpl<Serie> res = new PageImpl<Serie>(
				rep.findAll().stream()
                .filter(f -> f.getGenreList().contains(id))
                .filter(f -> f.isStatus() == true)
                .collect(Collectors.toList()) 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Serie> showByGenreAndLangue(Long genre, Long langue, Pageable p){
		refresh();
		PageImpl<Serie> res = new PageImpl<Serie>(
				rep.findAll().stream()
                .filter(f -> f.getGenreList().contains(genre))
                .filter(f -> f.getLangue().contains(langue))
                .filter(f -> f.isStatus() == true)
                .collect(Collectors.toList()) 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Serie> search(String n, Pageable p) {
		refresh();
		return rep.findByNameContainingOrOverviewContaining(n, n, p);
	}
	
	public Page<Serie> searchByGenre(String n, Long genre, Pageable p) {
		refresh();
		PageImpl<Serie> res = new PageImpl<Serie>(
				rep.findByNameContainingOrOverviewContaining(n, n)
				.stream()
                .filter(f -> f.getGenreList().contains(genre))
                .filter(f -> f.isStatus() == true)
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
		
	}
	
	public Page<Serie> searchByLangue(String n, Long langue, Pageable p) {
		refresh();
		PageImpl<Serie> res = new PageImpl<Serie>(
				rep.findByNameContainingOrOverviewContaining(n, n)
				.stream()
                .filter(f -> f.getLangue().contains(langue))
                .filter(f -> f.isStatus() == true)
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Serie> searchByLangueAndGenre(String n, Long langue, Long genre, Pageable p) {
		refresh();
		PageImpl<Serie> res = new PageImpl<Serie>(
				rep.findByNameContainingOrOverviewContaining(n, n)
				.stream()
                .filter(f -> f.getLangue().contains(langue))
                .filter(f -> f.getGenreList().contains(genre))
                .filter(f -> f.isStatus() == true)
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}

	public Serie upadte(Long id, Serie g) {

		g.setIdSerie(id);
		g.setActeurs(rep_actor.findAllById(g.getActeurList()));
		g.setGenres(genreRep.findAllById(g.getGenreList()));
		g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
		g.setList_langues(rep_langue.findAllById(g.getLangue()));
		refresh();
		return rep.save(g);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Serie> showById( Long id) {

		refresh();
		return rep.findById(id);

	}
	
	//Lier  Serie   genres
	public void addSerieGenre(Serie serie, Genre genre) {

		SerieGenre serieGenre = new SerieGenre();

		//serieGenre.setSerie(serie);

		//userRole.setUser(user);

		/*if (role == null) {
			role = roleService.findDefaultRole();
			
		}*/
		//serieGenre.setGenre(genre);

		serieGenreRep.save(serieGenre);

	}
	
	public void addSerieGenreId(Long serie, Long genre) {

		SerieGenre serieGenre = new SerieGenre();

		serieGenre.setSerie(serie);

		serieGenre.setGenre(genre);

		serieGenreRep.save(serieGenre);


	}

	public Serie findByName(String name) {
		refresh();
		return rep.findByNameIgnoreCase(name);
	}
	
	public List<Serie> top10(){
		refresh();
		return rep.findByTop10True();
	}
	
	public Serie Addtop10(Long id, boolean status){
		
		Serie r =  rep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}
	
	public Serie top(){
		
		return rep.findByTopTrue();
	}
	
	public Serie Addtop(Long id, boolean status){
		
		Serie r =  rep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}
	
	
	public Serie checktoplimit() {
		
		if(rep.findByTopTrue() != null) {
			
			return rep.findByTopTrue();
		}
		
		else {
			
			return null;
		}
	}
	
	//Si null la limite n'est pas encore atteinte
	
	public List<Serie> checktop10limit() {
		
		if(rep.findByTop10True().size() <=10) {
			
			return null;
		}
		else {
			
			return rep.findByTop10True();
		}
	}


	

}
