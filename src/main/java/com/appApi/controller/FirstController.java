package com.appApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

	@GetMapping("/Hi")
	public String test() {
		return "Deu certo!";
	}
	
	
	

	
}












