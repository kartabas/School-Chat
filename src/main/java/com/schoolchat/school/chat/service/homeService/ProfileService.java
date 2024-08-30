package com.schoolchat.school.chat.service.homeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.model.homeModels.ProfileModel;
import com.schoolchat.school.chat.repository.homeRepository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	public ProfileService() {
		this.profileRepository = profileRepository;
	}

	public ProfileModel getProfileById(Integer id) {
		return profileRepository.findById(id).orElse(null);
	}

}
