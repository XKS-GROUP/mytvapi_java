package com.mytv.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.user.model.Role;



@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);

}
