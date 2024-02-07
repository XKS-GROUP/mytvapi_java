package com.mytv.api.service.gestUser;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestUser.User;

@AutoConfiguration
public interface UserService {
	
	
	User create(User u);
	List<User> show();
	List<User> showById(Long id);
	User upadte(Long id, User p);
	Boolean delete(Long id);

}
