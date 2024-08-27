package com.mytv.api.firebase.service;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.dto.EmailDTO;
import com.mytv.api.dto.OtpConfirmDTO;
import com.mytv.api.firebase.model.Otp;
import com.mytv.api.firebase.repository.OTPRepository;
import com.mytv.api.util.service.NotificationService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OtpService {

	@Autowired
	private OTPRepository rep;
	
	@Value("${otp.time}")
	private int min;

	@Autowired
	NotificationService notification;

	public Otp create(Otp otp) {
		
		
		return rep.save(otp);

	}
	
	public Otp createOTP(EmailDTO email) {
		
		Otp otp = new Otp();
		otp.setEmail(email.getValue());
		Instant creation = Instant.now();
		   
        Instant expiration = creation.plus(min, MINUTES);
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);

        otp.setCreation(creation);
        otp.setExpiration(expiration);
        otp.setOtp(code);
        
        return rep.save(otp);
		
	}

	public Boolean comfirmOtp(OtpConfirmDTO req) {
		
			
		Otp otp = findByODT(req.getOtp());
		
		if(otp.getEmail().equalsIgnoreCase(req.getEmail()) ) {
			
			return true;
		}
		else {
			
			return false;
		}
			
	}
	
	public Boolean valideOtp(String code) {
		
		Otp otp = rep.findByOtp(code);
		
		if(otp.getExpiration().isAfter(Instant.now())) {
			
			return false;
		}
		
		else {
			
			return true;
		}
	}
	
	
	public List<Otp> show() {

		return rep.findAll();
	}
	
	public Page<Otp> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public Otp update(Long id, Otp u) {

		u.setId(id);

		return rep.save(u);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}
	
	public List<Otp> findByEmail(String email) {
		
		return rep.findByEmail(email);
	}
	
	public Otp findByODT(String opt) {
		
		return rep.findByOtp(opt);
	}
	
	public boolean Compare_Email_ODT(OtpConfirmDTO opt) {
		
		Otp t= findByODT(opt.getOtp());
		
		if(t.getEmail().equalsIgnoreCase(opt.getEmail())) {
			
			return true;
			
		}
		else{
			
			return false;
		}
		
	}
	
	public Page<Otp> findByEmail(String email, Pageable p) {
		
		return rep.findByEmail(email, p);
	}

	public Optional<Otp> showById( Long id) {

		return rep.findById(id);

	}
	


}
