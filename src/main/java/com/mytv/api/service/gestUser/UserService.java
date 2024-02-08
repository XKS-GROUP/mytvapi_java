package com.mytv.api.service.gestUser;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestUser.User;

@AutoConfiguration
public interface UserService {
	
	
	User create(User u);
	List<User> show();
	Optional<User> showById(final Long id);
	User upadte(Long id, User p);
	Boolean delete(Long id);

}
