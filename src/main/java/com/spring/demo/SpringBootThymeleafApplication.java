package com.spring.demo;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.demo.entities.User;
import com.spring.demo.repository.UserRepository;

@SpringBootApplication
public class SpringBootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeleafApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void createUser() {
		userRepository.save(new User("Nikhil", "ndhiwar10@gmail.com", "455212", 10, new Date("2/4/1991")));
		userRepository.save(new User("Raj", "raj11@gmail.com", "74895", 20, new Date("12/6/1992")));
		userRepository.save(new User("Koaml", "komal55@gmail.com", "1452", 30, new Date("9/1/1995")));
		userRepository.save(new User("Sonam", "sonam@gmail.com", "Sm5544", 50, new Date("2/9/1990")));
		userRepository.save(new User("Harish", "harish74@gmail.com", "1554", 20, new Date("5/6/2010")));
	}
}
