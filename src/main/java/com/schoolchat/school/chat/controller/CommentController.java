package com.schoolchat.school.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.CommentModels.CommentModel;
import com.schoolchat.school.chat.repository.CommentRepository.CommentRepository;
import com.schoolchat.school.chat.service.UsersService;
import com.schoolchat.school.chat.service.commentService.CommentService;
import com.schoolchat.school.chat.service.homeService.PostService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({ "/profile/comment", "/home/comment" })
public class CommentController extends HttpServlet {
	@Autowired
	private CommentService commentService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private PostService postService;

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
	public List<CommentModel> getCommentsUnderPost(@PathVariable Integer postId) {
		if (commentService.getListCommentsUnderPost(postId) == null) {
			return null;

		}
		return commentService.getListCommentsUnderPost(postId);
	}

	@GetMapping("/postcountcomments/{postId}")
	@ResponseBody
	public int getListCommentsUnderPost(@PathVariable Integer postId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			return postService.getCountCommentUnderPost(postId);
		}
		return 0;
	}


	@PostMapping("/savecomment")
	public ResponseEntity<?> saveComment(@RequestBody CommentModel sendComment, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		if (session != null) {

			UsersModel user = (UsersModel) session.getAttribute("userLogin");
			if (user == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			commentService.saveUserComment(sendComment.getPostId(),
					user,
					sendComment.getProfileModel(),
					sendComment.getCommentMessage(), sendComment.getCommentTime());
			return ResponseEntity.ok().build();

		} else {
			System.out.println("dont saveComment: " + sendComment.getCommentMessage());
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.noContent().build();
	}

}
