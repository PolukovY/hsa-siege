package com.levik.stressTestingApp;

import com.levik.stressTestingApp.service.UserService;
import com.levik.stressTestingApp.service.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;
import java.util.stream.IntStream;


@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class StressTestingAppApplication implements CommandLineRunner {

	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(StressTestingAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		IntStream.range(0, 1000).forEach(it -> {
			UserModel userModel = new UserModel();
			userModel.setName(UUID.randomUUID().toString());
			userModel.setEmail(UUID.randomUUID().toString());
			userService.create(userModel);
		});

		log.info("Up is ready..");
	}
}
