package com.schoolchat.school.chat.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolchat.school.chat.model.homeModels.SchoolPostsModel;
import com.schoolchat.school.chat.service.homeService.SchoolPostService;

@RestController
@RequestMapping("/schoolpost")
public class SchoolPostController {

	@Autowired
	private SchoolPostService schoolPostService;

	public SchoolPostController(SchoolPostService schoolPostService) {
		this.schoolPostService = schoolPostService;
	}

	@GetMapping("/{id}")
	public SchoolPostsModel getSchoolPostById(@PathVariable Integer id) {
		SchoolPostsModel schoolPost = schoolPostService.getSchoolPosts(id);
		System.out.println(schoolPost);
		return schoolPost;
	}

}
