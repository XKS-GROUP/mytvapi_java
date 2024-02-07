package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestUser.Role;

public interface RoleRepository  extends  JpaRepository<Role, Long>{

}
