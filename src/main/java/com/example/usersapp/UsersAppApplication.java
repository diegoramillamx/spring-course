package com.example.usersapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.usersapp.entities.User;
import com.example.usersapp.repositories.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner {

  @Autowired
  private Faker faker;

  @Autowired
  private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UsersAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 10000; i++) {
      User user = new User();
      user.setUsername(faker.name().username());
      user.setPassword(faker.gameOfThrones().dragon());
      userRepository.save(user);
    }
	}

}
