package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring.dto.MovieDTO;
import com.example.spring.service.IMovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
	
	private final IMovieService movieService;
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(MovieDTO movieDTO, RedirectAttributes rtts) {
		log.info("movieDTO : {}", movieDTO);
		
		Long mno = movieService.register(movieDTO);
		
		rtts.addFlashAttribute("msg", mno);
		
		return "redirect:/movie/list";
	}
}
