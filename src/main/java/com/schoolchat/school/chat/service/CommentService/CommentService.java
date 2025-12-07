package com.schoolchat.school.chat.service.commentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.CommentModels.CommentModel;
import com.schoolchat.school.chat.model.homeModels.ProfileModel;
import com.schoolchat.school.chat.repository.CommentRepository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public CommentService() {
		this.commentRepository = commentRepository;
	}

	public CommentModel findById(Long id) {
		return (CommentModel) commentRepository.findAll();
	}

	public CommentModel getComment(Long id) {
		return commentRepository.findByCommentId(id);
	}

	public CommentModel saveUserComment(Long postId, UsersModel userId, ProfileModel profileModel, String message,
			String sendTime) {
		if (postId == null || userId == null) {
			return null;
		}

		try {
			CommentModel commentModel = new CommentModel();

			commentModel.setPostId(postId);
			commentModel.setUsersModel(userId);
			commentModel.setCommentMessage(message);
			commentModel.setCommentTime(sendTime);
			commentModel.setProfileModel(profileModel);

			return commentRepository.save(commentModel);
		} catch (DataIntegrityViolationException e) {

			System.err.println("Error in create new comment: " + e.getMessage());
			return null;
		} catch (Exception e) {

			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}

	public List<CommentModel> getListCommentsUnderPost(Integer postId) {
		return commentRepository.findByPostId(postId);
	}

	public CommentModel deleteComment(Long id) {

		return commentRepository.deleteByCommentId(id);
	}

	public List<CommentModel> getAllComments() {
		return commentRepository.findAll();
	}

}
