package com.schoolchat.school.chat.service.homeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.model.homeModels.SchoolPostsModel;
import com.schoolchat.school.chat.repository.homeRepository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public PostService() {
		this.postRepository = postRepository;
	}

	public PostModel findById(Integer id) {
		return (PostModel) postRepository.findAll();
	}

	public PostModel getPost(Integer id) {
		return postRepository.findById(id).orElse(null);
	}

	public PostModel saveUserPost(String schoolId, UsersModel userId, String message, String sendTime,
			String postImage) {
		if (schoolId == null || userId == null) {
			return null;
		}

		try {
			PostModel postModel = new PostModel();

			postModel.setSchoolId(schoolId);
			postModel.setUsersModel(userId);
			postModel.setMeassage(message); 
			postModel.setTime(sendTime);
			postModel.setPostImage(postImage);

			
			return postRepository.save(postModel);
		} catch (DataIntegrityViolationException e) {
		
			System.err.println("Error in create new post: " + e.getMessage());
			return null;
		} catch (Exception e) {
			
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}

	public PostModel getLastPostModel(UsersModel userId) {

		return (PostModel) postRepository.findByUsersModel(userId);
	}





	public List<PostModel> getPostUserList(UsersModel userId) {
		return  postRepository.findByUsersModel(userId);
		
	}


	public List<PostModel> getAllPosts() {
		return (List<PostModel>) postRepository.findAll();
	}

		public List<PostModel> getAllSchoolPosts(String schoolId) {
		return postRepository.findBySchoolId(schoolId);
	}


}
