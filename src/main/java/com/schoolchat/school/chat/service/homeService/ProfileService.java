package com.schoolchat.school.chat.service.homeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
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

	public ProfileModel getProfileByUserIModel(UsersModel usersModel) {
		return profileRepository.findByUsersModel(usersModel);
	}

	public ProfileModel getProfileByUserId(Integer usersModelId) {
		return profileRepository.findByUsersModelId(usersModelId);
	}

	public ProfileModel savProfileModel(ProfileModel profileModel) {
		return profileRepository.save(profileModel);
	}

	public ProfileModel getUserModelByIdCheck(ProfileModel profileModelData) {

		ProfileModel profileModel = profileRepository.findByUsersModel(profileModelData.getUsersModel());
		if (profileModel == null) {
			return new ProfileModel();
		}
		 else {
			return profileModel;
		}

	}

	public ProfileModel updateProfileModel(ProfileModel profileModel) {
		return profileRepository.save(profileModel);
	}

	


}
