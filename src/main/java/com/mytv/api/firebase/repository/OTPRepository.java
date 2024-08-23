package com.mytv.api.firebase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.firebase.model.Otp;

public interface OTPRepository extends JpaRepository<Otp, Long>{

	List<Otp> findByEmail(String email);
	
	Otp findByOtp(String email);

	Page<Otp> findByEmail(String email, Pageable p);
}
