package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;

@RestController
public class WelcomeController {

//    @GetMapping("/welcome")
//    public String showWelcome() {
//        return "welcome";
//    }
    
    @GetMapping("/welcome")
    public ModelAndView showWelcome(ModelAndView model) {
        model.setViewName("welcome");
        return model;
    }
    
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//    }
    

}
