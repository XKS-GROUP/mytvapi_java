package com.mytv.api.service.gestMedia;

import java.util.List;

import com.mytv.api.model.gestMedia.Language;

public interface LangService {
	
	Language create(Language u);
	List<Language> show();
	List<Language> showById(Long id);
	Language upadte(Long id, Language p);
	Boolean delete(Long id);

}
