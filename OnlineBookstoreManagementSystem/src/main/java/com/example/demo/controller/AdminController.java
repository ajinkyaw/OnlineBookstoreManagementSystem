package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Admin;
import com.example.demo.model.User;
import com.example.demo.service.JDBCService;

@RestController
public class AdminController {
	
	private final JDBCService jdbcService;
	
	public AdminController(JDBCService jdbcService) {
		this.jdbcService = jdbcService;
	}
	
	@GetMapping("/adminRegister")
	public ModelAndView showAdminRegistrationForm(ModelAndView model) {
		model.addObject("admin", new Admin());
		model.setViewName("adminRegister");
		return model;
	}

	@GetMapping("/adminLogin")
	public ModelAndView showAdminLoginForm(ModelAndView model) {
		System.out.println("bcshdcjsbjhc");
		model.setViewName("adminLogin");
		return model;
	}

	@PostMapping("/adminRegister")
	public ModelAndView registerAdmin(Admin admin, ModelAndView model) {
		System.out.println("bcshdcjsbjhc");
		boolean registrationSuccess = jdbcService.registerAdmin(admin);
		System.out.println(registrationSuccess);
		if (registrationSuccess == false) {
			model.addObject("message","Registration failed");
			model.setViewName("adminRegister");
			return model;

		} else {
			model.addObject("message","Registration successful");
			model.setViewName("adminLogin");
			return model;
		}
	}

	@PostMapping("/adminLogin")
	public ModelAndView loginAdmin(User user, ModelAndView model) {

		boolean loginSuccess = jdbcService.loginAdmin(user);

		if (loginSuccess) {
			System.out.println("login Successful!!!!");
			model.addObject("message","login Successful!!!!");
			model.setViewName("adminDashboard");
			return model;
		} else {
			System.out.println("Username is not registered");
			model.addObject("message","login failed");
			model.setViewName("adminLogin");
			return model;
		}
	}
}
