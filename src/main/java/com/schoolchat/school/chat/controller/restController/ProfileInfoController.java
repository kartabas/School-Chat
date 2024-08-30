package com.schoolchat.school.chat.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolchat.school.chat.model.homeModels.ProfileModel;
import com.schoolchat.school.chat.service.homeService.ProfileService;

@RestController
@RequestMapping("/info")
public class ProfileInfoController {

	@Autowired
	private ProfileService profileService;

	public ProfileInfoController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@GetMapping("/{id}")
	public ProfileModel getUserProfileById(@PathVariable Integer id) {
		ProfileModel userProfile = profileService.getProfileById(id);
		System.out.println(userProfile);
		return userProfile;
	}



}
