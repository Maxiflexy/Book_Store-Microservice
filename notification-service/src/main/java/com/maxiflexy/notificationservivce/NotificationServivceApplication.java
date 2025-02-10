package com.maxiflexy.notificationservivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class NotificationServivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServivceApplication.class, args);
	}

}
