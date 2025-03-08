package com.schoolchat.school.chat.service.homeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
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

	public SchoolPostsModel savePostInUserSchool(String schoolId, PostModel postModelId, UsersModel userId) {

		if (schoolId == null || postModelId == null) {
			return null;
		}

		try {
			SchoolPostsModel schoolPostsModel = new SchoolPostsModel();
			schoolPostsModel.setSchoolId(schoolId);
			schoolPostsModel.setPostModel(postModelId);

			schoolPostsModel.setUsersModel(userId);
			return schoolPostRepository.save(schoolPostsModel);
		} catch (DataIntegrityViolationException e) {
			System.err.println("Error in create new post: " + e.getMessage());
			return null;
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			return null;
		}

	}

	public List<SchoolPostsModel> getAllSchoolPosts(String schoolId) {
		return schoolPostRepository.findBySchoolId(schoolId);
	}



	public SchoolPostsModel getPostByPostModel(PostModel postModel) {
		return   schoolPostRepository.findByPostModel(postModel);
  }


	public void deleteBySchoolPostId(Integer schoolPostsModel) {
		schoolPostRepository.deleteBySchoolPostId(schoolPostsModel);


	}



}
