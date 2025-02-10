package com.maxiflexy.notificationservivce;

import org.springframework.boot.SpringApplication;

public class TestNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(NotificationServivceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
