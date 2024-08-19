package com.mytv.api.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CronTache {

	
	//@Scheduled(cron = "0 0 0 * * ?") // Exécuter à minuit tous les jours
}
