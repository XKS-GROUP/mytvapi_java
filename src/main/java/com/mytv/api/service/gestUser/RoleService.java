package com.mytv.api.service.gestUser;

import java.util.List;

import com.mytv.api.model.gestUser.Role;


public interface RoleService {


	Role create(Role u);
	List<Role> show();
	//List<User> showById();
	Role upadte(Long id, Role p);
	Boolean delete(Long id);


}
