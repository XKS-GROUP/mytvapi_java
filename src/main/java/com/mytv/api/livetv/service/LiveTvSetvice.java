package com.mytv.api.livetv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.config.AlgoliaConfig;
import com.mytv.api.dto.LiveTvDTO;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.repository.FavLivetvRepository;
import com.mytv.api.livetv.repository.LiveTvRepository;
import com.mytv.api.ressource.repository.CategoryLrRepository;
import com.mytv.api.ressource.repository.LangRepository;
import com.mytv.api.ressource.repository.PaysRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class LiveTvSetvice {


	@Autowired
	private LiveTvRepository rep;
	
	@Autowired
	private PaysRepository rep_pays;
	
	@Autowired 
	FavLivetvRepository rep_fav_livetv;
	
	@Autowired
	private CategoryLrRepository rep_categ;
	
	@Autowired
	private LangRepository rep_langue;
	
	@Autowired
	private AlgoliaConfig algoClient;

	public LiveTv create(LiveTv g) {

		//
		g.setLangues(rep_langue.findAllById(g.getLangue()));
		g.setPays(rep_pays.findAllById(g.getCountry()));
		g.setListCateg(rep_categ.findAllById(g.getCountry()));
		
		
		var resp = algoClient.searchClient().saveObject("livetv", g);
		
		algoClient.searchClient().waitForTask("livetv", resp.getTaskID());
		
		return rep.save(g);

	}
	
	
	@Autowired
	FavLivetvRepository rep_fav_tv;
	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			//Retirer les favories de tous les users
			rep_fav_tv.findAll().forEach(
					
					f -> {
						
						f.getLivetv().setFavorie(false);
					}
					
				);
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			System.out.println("Les favories de "+u.getUsername());
			
			//Afficher que les favories du l utilisateur actuelle
			rep_fav_tv.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getLivetv().setFavorie(true);
					}
					
				);
		}
		
		List<LiveTv> l = rep.findAll();
				
				for(int i =0; i <  rep.findAll().size(); i++) {
					
					  l.get(i).setLangues(rep_langue.findAllById(l.get(i).getLangue()));
					  l.get(i).setPays(rep_pays.findAllById(l.get(i).getCountry()));
					  l.get(i).setListCateg(rep_categ.findAllById(l.get(i).getIdcategories()));
					}
		
	}
	
	
	public List<LiveTv> show() {
		
		List<LiveTv> l = rep.findAll();
		
		for(int i =0; i <  rep.findAll().size(); i++) {
			
			  l.get(i).setLangues(rep_langue.findAllById(l.get(i).getLangue()));
			  l.get(i).setPays(rep_pays.findAllById(l.get(i).getCountry()));
			  l.get(i).setListCateg(rep_categ.findAllById(l.get(i).getIdcategories()));
			}
		
		
		return l;
	}
	
	public List<LiveTv> show_front() {
		
		List<LiveTv> l = rep.findAll();
		
		for(int i =0; i <  rep.findAll().size(); i++) {
			
			  l.get(i).setLangues(rep_langue.findAllById(l.get(i).getLangue()));
			  l.get(i).setPays(rep_pays.findAllById(l.get(i).getCountry()));
			  l.get(i).setListCateg(rep_categ.findAllById(l.get(i).getIdcategories()));
			}
		
		
		return l.stream().filter(f ->f.isStatus()).toList();
	}
	
	
	public Page<LiveTv> similaire_show(Long id, Pageable p) {
		
		refresh();
		LiveTv m =  rep.findById(id).get();
		
		PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findAll(p).stream()
				   .filter(rd -> rd.getIdcategories().containsAll(m.getIdcategories()))
				   .toList() 
				   , p
				   , rep.findAll().size());
		return res;
	}
	
	
	public LiveTvDTO dto(LiveTv l) {
		
		LiveTvDTO dto = new LiveTvDTO();
		
		dto.setAccessFree(l.getAccessFree());
		dto.setAddDate(l.getAddDate());
		dto.setCountry(l.getCountry());
		dto.setIdcategories(l.getIdcategories());
		dto.setIdLiveTv(l.getIdLiveTv());
		dto.setLangue(l.getLangue());
		
		
		dto.setName(l.getName());
		dto.setOverview(l.getOverview());
		
		dto.setStatus(l.isStatus());
		dto.setSvr1_url(l.getSvr1_url());
		dto.setSvr2_url(l.getSvr2_url());
		dto.setSvr3_url(l.getSvr3_url());
		dto.setTop(l.isTop());
		dto.setTop10(l.isTop10());
		dto.setTvEmbedCode(l.getTvEmbedCode());
		dto.setTvLogo_path(l.getTvLogo_path());
		
		

		dto.setLangues(rep_langue.findAllById(l.getLangue()));
		dto.setPays(rep_pays.findAllById(l.getCountry()));
		dto.setListCateg(rep_categ.findAllById(l.getCountry()));
		return dto;
		
	}
	
	public List<LiveTvDTO> showDTO() {
		
		List<LiveTvDTO> livetv = new ArrayList<>();
		
		for(int i =0; i <  rep.findAll().size(); i++) {
			
		  livetv.add(dto(rep.findAll().get(i)));
		}
		
		return livetv;
	}
	
	public Page<LiveTv> showPage(Pageable p) {

		refresh();
		Page<LiveTv> l = rep.findAll(p);
		
		l.get().toList().forEach(  
				
				lv -> {
					lv.setLangues(rep_langue.findAllById(lv.getLangue()));
					lv.setPays(rep_pays.findAllById(lv.getLangue()));
					lv.setListCateg(rep_categ.findAllById(lv.getIdcategories()));
				}
				
		);
		
		return l;//return rep.findAll(p);
		
	}

	public Page<LiveTv> search(String val, Pageable p) {
		show();
		return rep.findByNameContainingOrOverviewContaining(val, val, p);
		
	}

	public LiveTv update(Long id, LiveTv u) {

		LiveTv old = rep.findById(id).get();

		old = u;

		old.setIdLiveTv(id);
		show();
		rep.save(old);
		
		algoClient.refreshLivetv();
		
		return old;
	}

	public Boolean delete(Long id) {
		refresh();
		rep.deleteById(id);
		algoClient.refreshLivetv();
		return null;

	}

	public Optional<LiveTv> showById(final Long id) {
		refresh();
		return rep.findById(id);
	}
	
	
	@SuppressWarnings("unused")
	public Page<LiveTv> filtre_complet(Long categ, Long langue, Long pays, Pageable p){
		refresh();
		PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findAll(p).stream()
				   .toList() 
				   , p
				   , rep.findAll().size());
		
		if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .toList()
					   , p
					   , rep.findAll().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findAll().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findAll().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findAll().size());
		}
		else {
			return res;
		}
		
	};
	
	@SuppressWarnings("unused")
	public Page<LiveTv> filtre_complet_front(Long categ, Long langue, Long pays, Pageable p){
		refresh();
		PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findAll(p).stream()
				   .filter(f -> f.isStatus())
				   .toList() 
				   , p
				   , rep.findAll(p).stream()
				   .filter(f -> f.isStatus())
				   .toList() .size());
		
		if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<LiveTv>(rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	@SuppressWarnings("unused")
	public Page<LiveTv> filtre_recherche_complet(String val,Long categ, Long langue, Long pays, Pageable p){
		refresh();
		PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .toList() 
				   , p
				   , rep.findAll(p).stream()
				   .toList().size());
		
		
		if(val==null || val.isBlank() ||val.isEmpty()) {
			
			return filtre_complet(categ, langue, pays, p);
		}
		else if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .toList().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   ,rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   ,rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public Page<LiveTv> filtre_recherche_complet_front(String val,Long categ, Long langue, Long pays, Pageable p){
		refresh();
		PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .filter(f -> f.isStatus())
				   .toList() 
				   , p
				   , rep.findAll(p).stream()
				   .filter(f -> f.isStatus())
				   .toList() .size());
		
		if(val==null || val.isBlank() ||val.isEmpty()) {
			
			return filtre_complet(categ, langue, pays, p);
		}
		else if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   ,rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<LiveTv>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getIdcategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	
	public LiveTv findByName(String name) {
		refresh();
		return rep.findByName(name);
	}
	
	public List<LiveTv> top10(){
		refresh();
		return rep.findByTop10True();
	}
	
	public LiveTv Addtop10(Long id, boolean status){
		
		LiveTv lv =  rep.findById(id).get();
		lv.setTop10(status);
		refresh();
		return lv;
		
	}
	
    public LiveTv top(){
    	refresh();
		return rep.findByTopTrue();
	}
	
	public LiveTv Addtop(Long id, boolean status){
		
		LiveTv lv =  rep.findById(id).get();
		lv.setTop(status);
		refresh();
		return lv;
	}
	
	public LiveTv checktoplimit() {
		refresh();
		if(rep.findByTopTrue() != null) {
			
			return rep.findByTopTrue();
		}
		
		else {
			
			return null;
		}
	}
	
	//Si null la limite n'est pas encore atteinte
	public List<LiveTv> checktop10limit() {
		refresh();
		if(rep.findByTop10True().size() <=10) {
			
			return null;
		}
		else {
			
			return rep.findByTop10True();
		}
	}

}
