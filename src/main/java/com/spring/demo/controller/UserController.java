package com.spring.demo.controller;

import javax.validation.Valid;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.demo.entities.User;
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

	@GetMapping("/signup")
	public String showSignUpForm(User user) {
		return "user/add-user";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/add-user";
		}

		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "user/showUser";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    User user = userRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	     
	    model.addAttribute("user", user);
	    return "user/update-user";
	}

	@PostMapping(value = "/update/{id}")
	public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "user/update-user";
		}
		if (userRepository.existsById(id)) {
			User existUser = userRepository.getOne(id);
			existUser.setUserName(user.getUserName());
			existUser.setAge(user.getAge());
			existUser.setDob(user.getDob());
			existUser.setEmail(user.getEmail());
			existUser.setPassword(user.getPassword());

			userRepository.save(existUser);
			model.addAttribute("users", userRepository.findAll());
			return "user/showUser";

		}
		return "error";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			model.addAttribute("users", userRepository.findAll());
			return "user/showUser";
		}
		return "error";
	}

}
