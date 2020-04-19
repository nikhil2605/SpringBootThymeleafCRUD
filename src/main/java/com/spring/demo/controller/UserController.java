package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.demo.repository.UserRepository;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "/")
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("copyright", "Welcome to Thymeleaf");
		return "user/showUser";
	}

}
