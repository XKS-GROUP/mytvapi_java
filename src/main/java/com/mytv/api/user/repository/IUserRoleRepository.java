package com.mytv.api.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.user.model.UserRole;




@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long>{

	List<UserRole> findAllByUserId(Long id);

}
