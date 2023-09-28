package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.JDBCService;

@RestController
public class GenreController {
	
	private final JDBCService jdbcService;

	public GenreController(JDBCService jdbcService) {
		this.jdbcService = jdbcService;
	}
	
    @GetMapping("/genre-selection")
    public ModelAndView showGenreSelectionPage(ModelAndView model) {
        List<String> genreList = jdbcService.getCategory();
        model.addObject("genreList", genreList);
        model.setViewName("genre-selection");
        return model;
    }

    // ... other controller methods
}
