package com.mytv.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class MytvapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MytvapiApplication.class, args);
	}

}
