package com.schoolchat.school.chat.service.homeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.model.homeModels.SchoolPostsModel;
import com.schoolchat.school.chat.repository.homeRepository.SchoolPostRepository;

@Service
public class SchoolPostService {

	@Autowired
	private SchoolPostRepository schoolPostRepository;

	public SchoolPostService() {
		this.schoolPostRepository = schoolPostRepository;
	}

	public SchoolPostsModel getSchoolPosts(Integer schoolPostId) {
		return schoolPostRepository.findBySchoolPostId(schoolPostId).orElse(null);
	}

}
