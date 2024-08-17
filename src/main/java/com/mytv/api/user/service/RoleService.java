package com.mytv.api.user.service;

import java.util.List;

import com.mytv.api.user.model.Role;


public interface RoleService {


	Role create(Role u);
	List<Role> show();
	//List<User> showById();
	Role upadte(Long id, Role p);
	Boolean delete(Long id);


}
