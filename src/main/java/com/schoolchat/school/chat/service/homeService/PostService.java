package com.schoolchat.school.chat.service.homeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.CommentModels.CommentModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.repository.homeRepository.PostRepository;
import com.schoolchat.school.chat.service.CommentService.CommentService;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentService commentService;

	public PostService() {
		this.postRepository = postRepository;
	}

	public PostModel findById(Integer id) {
		return (PostModel) postRepository.findAll();
	}

	public PostModel getPost(Integer id) {
		return postRepository.findById(id).orElse(null);
	}

	// Save Post Servise
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
			postModel.setSendTime(sendTime);
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

	// Show all post of user
	public List<PostModel> getPostUserList(UsersModel userId) {
		return postRepository.findByUsersModel(userId);

	}

	// Show all post
	public List<PostModel> getAllPosts() {
		return (List<PostModel>) postRepository.findAll();
	}

	// Show all post of school
	public List<PostModel> getAllSchoolPosts(String schoolId) {
		return postRepository.findBySchoolId(schoolId);
	}

	// Update Post
	public PostModel updatePost(Integer id, String message, String sendTime, String postImage) {

		PostModel postModel = postRepository.findById(id).orElse(null);
		postModel.setMeassage(message);
		postModel.setSendTime(sendTime);
		postModel.setPostImage(postImage);
		return postRepository.save(postModel);
	}

	// Delete Post
	public void deleteByPostId(Integer postId) {
		postRepository.deleteByPostId(postId);
	}

	public PostModel updatePostModel(PostModel postModel) {
		return postRepository.save(postModel);

	}




// Get the count of comments under a post
	public int getCountCommentUnderPost(Integer postId) {
		
		List<CommentModel> commentList = commentService.getListCommentsUnderPost(postId);
		if (commentList == null) {
			return 0;
		}
		return commentList.size();
	}




}
