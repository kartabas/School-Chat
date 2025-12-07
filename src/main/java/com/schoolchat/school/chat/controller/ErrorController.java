package com.schoolchat.school.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ErrorController {

	@GetMapping("/error")
	public String getErrorPage(RedirectAttributes redirectAttributes) {
		return "error_page"; // Return the name of the error page template
	}

}
