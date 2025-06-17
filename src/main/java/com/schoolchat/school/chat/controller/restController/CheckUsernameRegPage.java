package com.schoolchat.school.chat.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolchat.school.chat.service.UsersService;

@Controller
public class CheckUsernameRegPage {

	@Autowired
	UsersService usersService = new UsersService();

	public CheckUsernameRegPage(UsersService usersService) {
		this.usersService = usersService;
	}

	@PostMapping("/checkUsername")
	@ResponseBody
	public boolean checkUsername(@RequestParam( value = "username") String username) {
		System.out.println("Checking username: " + username);
		boolean isAvailable = usersService.checkUsername(username);
		if (isAvailable) {
			//System.out.println("Username is available.");
		} else {
			//System.out.println("Username is already taken.");
		}
		return isAvailable;
	}

}
