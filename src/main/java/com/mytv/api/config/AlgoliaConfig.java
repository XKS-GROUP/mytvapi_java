package com.mytv.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algolia.api.SearchClient;
import com.mytv.api.film.repository.FilmRepository;
import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.livetv.repository.LiveTvRepository;
import com.mytv.api.news.repository.ArticleRepository;
import com.mytv.api.podcast.repository.PodcastRepository;
import com.mytv.api.radio.repository.RadioRepository;
import com.mytv.api.serie.repository.SerieRepository;

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
    
    
    public SearchClient searchClient() {
    	return new SearchClient(applicationId, apiKey);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
    	
    	System.out.println(" initialisation des donné ...... je demmare une fois seulement");
    	
    	searchClient().deleteIndex("film");
    	searchClient().saveObjects("film", filmRep.findAll().stream().filter(g ->g.isStatus()).toList());
    	
        searchClient().deleteIndex("podcast");
		searchClient().saveObjects("podcast", podcastRep.findAll().stream().filter(g ->g.isStatus()).toList());
		
		searchClient().deleteIndex("livetv");
		searchClient().saveObjects("livetv", livetvRep.findAll().stream().filter(g ->g.isStatus()).toList());
		
		searchClient().deleteIndex("radio");
		searchClient().saveObjects("radio", radioRep.findAll().stream().filter(g ->g.isStatus()).toList());
		
		searchClient().deleteIndex("article");
		searchClient().saveObjects("article", articleRep.findAll().stream().filter(g ->g.isStatus()).toList());
		
		searchClient().deleteIndex("serie");
		searchClient().saveObjects("serie", serieRep.findAll().stream()
				.filter(g ->g.isStatus())
				.filter(f -> !f.getIdSaison().isEmpty()).toList());
		
		searchClient().deleteIndex("acteur");
		searchClient().saveObjects("acteur", acteurRep.findAll());

    }
    
    public void refreshFilm() {
    	searchClient().deleteIndex("film");
    	searchClient().saveObjects("film", filmRep.findAll().stream().filter(g ->g.isStatus()).toList());
    }
    
    public void refreshSerie() {
    	searchClient().deleteIndex("serie");
		searchClient().saveObjects("serie", serieRep.findAll().stream()
				.filter(g ->g.isStatus())
				.filter(f -> !f.getIdSaison().isEmpty()).toList());
    }
    
    public void refreshLivetv() {
    	
    	searchClient().deleteIndex("livetv");
    	//searchClient().replaceAllObjects("livetv", livetvRep.findAll().stream().filter(g ->g.isStatus()).toList(), 0);
		searchClient().saveObjects("livetv", livetvRep.findAll().stream().filter(g ->g.isStatus()).toList());
    }
    
    public void refreshRadio() {

		searchClient().deleteIndex("radio");
		searchClient().saveObjects("radio", radioRep.findAll().stream().filter(g ->g.isStatus()).toList());
    }
    
    public void refreshPodcast() {
    	searchClient().deleteIndex("podcast");
		searchClient().saveObjects("podcast", podcastRep.findAll().stream().filter(g ->g.isStatus()).toList());
    	
    }
    
    public void refreshArticle() {
    	
    	searchClient().deleteIndex("article");
		searchClient().saveObjects("article", articleRep.findAll().stream().filter(g ->g.isStatus()).toList());
    }
    
    public void refreshActeur() {
		searchClient().deleteIndex("acteur");
		searchClient().saveObjects("acteur", acteurRep.findAll());
    }


    
    
}