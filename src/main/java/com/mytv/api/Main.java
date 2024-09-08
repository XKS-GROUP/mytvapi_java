package com.mytv.api;

import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EntityScan
@ComponentScan
public class Main {

	public static void main(String[] args) throws Exception, URISyntaxException {
		
		  SpringApplication.run(Main.class, args);
		
	 }

	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}

}
