package com.schoolchat.school.chat.controller.restController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class ReactController {

	@GetMapping("/api")
	public String hello() {
		return "Hello from Spring Boot!";
	}

}
