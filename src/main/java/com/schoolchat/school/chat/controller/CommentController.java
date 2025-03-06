package com.schoolchat.school.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.CommentModels.CommentModel;
import com.schoolchat.school.chat.model.homeModels.ProfileModel;
import com.schoolchat.school.chat.repository.CommentRepository.CommentRepository;
import com.schoolchat.school.chat.service.CommentService.CommentService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({ "/profile/comment", "/home/comment"})
public class CommentController extends HttpServlet {
	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentRepository commentRepository;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public CommentModel getComment(@PathVariable Long id) {
		return commentService.getComment(id);
	}

	@GetMapping("/all")
	@ResponseBody
	public List<CommentModel> getAllCommentsPost() {

		return commentService.getAllComments();
	}

	@GetMapping("/postcomments/{postId}")
	@ResponseBody
	public List<CommentModel> getCommentsUnderPost(@PathVariable Long postId) {
		if(commentService.getListCommentsUnderPost(postId) == null) {
			return null;

		}
		return commentService.getListCommentsUnderPost(postId);
	}

	@GetMapping("/save")
	public CommentModel saveComment(Long postId, ProfileModel profileModel, String message,
			String sendTime, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel userId = (UsersModel) session.getAttribute("userLogin");

			return commentService.saveUserComment(postId, userId, profileModel, message, sendTime);
		} else {
			return null;
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.noContent().build();
	}

}
