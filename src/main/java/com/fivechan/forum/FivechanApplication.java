package com.fivechan.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fivechan.forum")
public class FivechanApplication {

	public static void main(String[] args) {
		SpringApplication.run(FivechanApplication.class, args);
	}

}
