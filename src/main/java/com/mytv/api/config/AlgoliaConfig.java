package com.mytv.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algolia.api.SearchClient;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.repository.FilmRepository;
import com.mytv.api.intervenant.model.Actor;
import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.intervenant.repository.DirectorRepository;
import com.mytv.api.intervenant.repository.PodcasterRepository;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.repository.LiveTvRepository;
import com.mytv.api.news.model.Article;
import com.mytv.api.news.repository.ArticleRepository;
import com.mytv.api.news.repository.CategArticleRepository;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.repository.PodcastRepository;
import com.mytv.api.podcastcateg.repository.CatPodcastRepository;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.repository.RadioRepository;
import com.mytv.api.ressource.repository.CategoryLrRepository;
import com.mytv.api.ressource.repository.LangRepository;
import com.mytv.api.ressource.repository.PaysRepository;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.repository.GenreRepository;
import com.mytv.api.serie.repository.SerieRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;


@Component
@Configuration
public class AlgoliaConfig {

    @Value("${algolia.application-id}")
    private String applicationId;

    @Value("${algolia.admin-api-key}")
    private String apiKey;
    
    @Autowired
    FilmRepository filmRep;
    
    @Autowired
    PodcastRepository podcastRep;
    
    @Autowired
    SerieRepository serieRep;
    
    @Autowired
    LiveTvRepository livetvRep;
    
    @Autowired
    RadioRepository radioRep;
    
    @Autowired
    ArticleRepository articleRep;
    
    @Autowired
    ActorRepository acteurRep;
    
    @Autowired
	GenreRepository genreRep;
    
    @Autowired
	private PaysRepository rep_pays;
	
	@Autowired
	private CategoryLrRepository rep_categ;
	@Autowired
	CategArticleRepository rep_article_categ;
	
	@Autowired
	private CatPodcastRepository  rep_pod_categ;
	
	@Autowired
	private LangRepository rep_langue;
	
	@Autowired
	private DirectorRepository rep_dirs;
	
	@Autowired
	private PodcasterRepository rep_podcasteur;
    
    
    public SearchClient searchClient() {
    	return new SearchClient(applicationId, apiKey);
    }
    
    
    /*
     * cette fontion s execute qu une seule fois au debut pour charger les donnes valide sur algolia
     * 
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
    	
    	System.out.println(" initialisation des donné ...... je demmare une fois seulement");
    	
    	//Chargement des films
    	List<Film> films = filmRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
		films.forEach(  
				
				g -> {
					g.setActeurs(acteurRep.findAllById(g.getActeurList()));
					g.setGenres(genreRep.findAllById(g.getGenreList()));
					g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
					g.setList_langues(rep_langue.findAllById(g.getLangue()));
				}
		);
    	searchClient().replaceAllObjects("film", films, 50);
    	
    	
    	
    	//Podcast
    	List<Podcast> podcasts = podcastRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
    	podcasts.forEach(  
				
				p -> {
					p.setList_langues(rep_langue.findAllById(p.getLangue()));
					p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
					p.setList_categories(rep_pod_categ.findAllById(p.getCategories()));
				}
				
		);
    	
		searchClient().replaceAllObjects("podcast", podcasts, 50);
		
		//Chargement Live tv
		List<LiveTv> livetvs = livetvRep.findAll().stream().filter(g ->g.isStatus()).toList();
			
		livetvs.forEach(
				
				g-> {
					g.setLangues(rep_langue.findAllById(g.getLangue()));
					g.setPays(rep_pays.findAllById(g.getCountry()));
					g.setListCateg(rep_categ.findAllById(g.getCountry()));
				  }
				);
			
		searchClient().replaceAllObjects("livetv", livetvs, 50);
		
		
		//Chargement Radio 
		
		List<Radio> radios = radioRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
		radios.forEach(  
				
				p -> {
					p.setList_langues(rep_langue.findAllById(p.getLangue()));
					p.setList_categories(rep_categ.findAllById(p.getCategories()));
				}
		);
		searchClient().replaceAllObjects("radio", radios, 50);
		
		//Chargement Article
		List<Article> articles = articleRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
		articles.forEach(  
				
				g -> {
					
					g.setList_categories(rep_article_categ.findAllById(g.getCategories()));
					
				}
			);
		searchClient().replaceAllObjects("radio", radios, 50);
		
		
		//Chargement serie
		List<Serie> series = serieRep.findAll().stream()
				.filter(g ->g.isStatus())
				.filter(f -> !f.getIdSaison().isEmpty()).toList();
		
		series.forEach(  
				
				g -> {
					g.setActeurs(acteurRep.findAllById(g.getActeurList()));
					g.setGenres(genreRep.findAllById(g.getGenreList()));
					g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
					g.setList_langues(rep_langue.findAllById(g.getLangue()));
				}
		);
		searchClient().replaceAllObjects("serie", series, 50);
		
		//Chargement acteurs
		
		 List<Actor> acteurs = acteurRep.findAll();
				 
		 acteurs.forEach(
		  
		  p -> p.setList_pays(rep_pays.findAllById(p.getPays()))
		  
		  );
		searchClient().replaceAllObjects("acteur", acteurs, 50);

    }
    
    /*
     * 
     * Les fonction refresh permettrons de rafraichir le contenue de chaque index apres une modification de donnees
     * 
     * 
     * 
     */
    
    
    public void refreshFilm() {
    	//Chargement des films
    	List<Film> films = filmRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
		films.forEach(  
				
				g -> {
					g.setActeurs(acteurRep.findAllById(g.getActeurList()));
					g.setGenres(genreRep.findAllById(g.getGenreList()));
					g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
					g.setList_langues(rep_langue.findAllById(g.getLangue()));
				}
		);
    	searchClient().replaceAllObjects("film", films, 50);
    }
    
    public void refreshSerie() {
    	
    	List<Serie> series = serieRep.findAll().stream()
				.filter(g ->g.isStatus())
				.filter(f -> !f.getIdSaison().isEmpty()).toList();
		
		series.forEach(  
				
				g -> {
					g.setActeurs(acteurRep.findAllById(g.getActeurList()));
					g.setGenres(genreRep.findAllById(g.getGenreList()));
					g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
					g.setList_langues(rep_langue.findAllById(g.getLangue()));
				}
		);
		searchClient().replaceAllObjects("serie", series, 50);
    }
    
    public void refreshLivetv() {
    	
    	List<LiveTv> livetvs = livetvRep.findAll().stream().filter(g ->g.isStatus()).toList();
		livetvs.forEach(
				
				g-> {
					g.setLangues(rep_langue.findAllById(g.getLangue()));
					g.setPays(rep_pays.findAllById(g.getCountry()));
					g.setListCateg(rep_categ.findAllById(g.getCountry()));
				  }
				);
		searchClient().replaceAllObjects("livetv", livetvs, 50);
    }
    
    public void refreshRadio() {

    	List<Radio> radios = radioRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
		radios.forEach(  
				
				p -> {
					p.setList_langues(rep_langue.findAllById(p.getLangue()));
					p.setList_categories(rep_categ.findAllById(p.getCategories()));
				}
		);		
		searchClient().replaceAllObjects("radio", radios, 50);
    }
    
    public void refreshPodcast() {
    	
    	//Podcast
    	List<Podcast> podcasts = podcastRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
    	podcasts.forEach(  
				
				p -> {
					p.setList_langues(rep_langue.findAllById(p.getLangue()));
					p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
					p.setList_categories(rep_pod_categ.findAllById(p.getCategories()));
				}
				
		);
    	
		searchClient().replaceAllObjects("podcast", podcasts, 50 ); 
		
    	
    }
    
    public void refreshArticle() {
    	
    	List<Article> articles = articleRep.findAll().stream().filter(g ->g.isStatus()).toList();
		
		articles.forEach(  
				
				g -> {
					
					g.setList_categories(rep_article_categ.findAllById(g.getCategories()));
					
				}
			);
		searchClient().replaceAllObjects("article", articles, 50);
    }
    
    public void refreshActeur() {
    	List<Actor> acteurs = acteurRep.findAll();
		 
		 acteurs.forEach(
		  
		  p -> p.setList_pays(rep_pays.findAllById(p.getPays()))
		  
		  );
		searchClient().replaceAllObjects("acteur", acteurs, 50);
    }

    
}