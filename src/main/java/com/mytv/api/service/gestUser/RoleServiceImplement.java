package com.mytv.api.service.gestUser;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.Role;
import com.mytv.api.repository.RoleRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RoleServiceImplement implements RoleService {


	private RoleRepository roleRep;

	@Override
	public Role create(Role s) {

		return roleRep.save(s);
	}

	@Override
	public List<Role> show() {

		return roleRep.findAll();
	}

	@Override
	public Role upadte(Long id, Role p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {

		roleRep.deleteById(id);

		return true;
	}

}
