package com.psd.eventplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.psd.eventplanner"})
public class EventPlannerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventPlannerBackendApplication.class, args);
	}

}
