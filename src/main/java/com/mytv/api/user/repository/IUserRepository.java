package com.mytv.api.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.user.model.User;



@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
    User findByPhone(String phone);
    User findByEmail(String email);
    User findByEmailOrPhone(String email, String phone);

    User findByIdAndPassword(Long id, String password);
    List<User> findByValideTrue();
    List<User> findByValideFalse();
    
    

}
