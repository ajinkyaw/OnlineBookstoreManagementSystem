package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Admin;
import com.example.demo.model.User;
import com.example.demo.service.JDBCService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

	private final JDBCService jdbcService;

	public UserController(JDBCService jdbcService) {
		this.jdbcService = jdbcService;
	}

	@GetMapping("/register")
	public ModelAndView showRegistrationForm(ModelAndView model) {
		model.addObject("user", new User());
		model.setViewName("register");
		return model;
	}

	@GetMapping("/login")
	public ModelAndView showLoginForm(ModelAndView model) {
		System.out.println("bcshdcjsbjhc");
		model.setViewName("login");
		return model;
	}
	
	@PostMapping("/login")
	public ModelAndView loginUser(@ModelAttribute User user, HttpSession session) {
		ModelAndView model = new ModelAndView();
	    Integer userId = jdbcService.loginUser(user); // Call your loginUser method

	    if (userId != 0) {
	    	System.out.println(userId);
	        // Login successful, store userId in the session
	        session.setAttribute("userId", userId);
	        model.setViewName("userDashboard");
			return model;
	    } else {
	    	String message = "Login Failed";
			model.addObject("message",message);
			 model.setViewName("login");
				return model;
	    }
	}

	@PostMapping("/register")
	public ModelAndView registerUser(User user, ModelAndView model) {
		System.out.println("bcshdcjsbjhc");
		boolean registrationSuccess = jdbcService.registerUser(user);
		System.out.println(registrationSuccess);
		if (registrationSuccess == false) {
			String message = "Registration failed, Please Register again with valid credentials";
			model.addObject("message",message);
			model.setViewName("register");
			return model;

		} else {
			String message = "Registration Successful, Please Login with valid credentials";
			model.addObject("message",message);
			model.setViewName("login");
			return model;
		}
	}



}
