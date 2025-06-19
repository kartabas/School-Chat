package com.schoolchat.school.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolchat.school.chat.service.homeService.PostService;
import com.schoolchat.school.chat.service.likesService.LikeService;

import jakarta.servlet.http.HttpServlet;

@Controller
@RequestMapping({ "/profile/like", "/home/like" })
public class LikePostController extends HttpServlet {

	@Autowired
	private PostService postService;

	@Autowired
	private LikeService likeService;

	public LikePostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/{postId}")
	@ResponseBody
	public Long likePost(@RequestParam Integer postId) {
		if (postService.likePost(postId)) {
			return postService.getLikeCount(postId);
		} else {
			return postService.getLikeCount(postId);
		}
	}

	@DeleteMapping("/{postId}")
	@ResponseBody
	public Long unlikePost(@PathVariable Integer postId) {
		if (postService.unlikePost(postId)) {
			return postService.getLikeCount(postId);
		} else {
			return postService.getLikeCount(postId);
		}
	}

	@GetMapping("/get/{postId}")
	@ResponseBody
	public Long getLikesCount(@PathVariable Integer postId) {
		Long likesCount = postService.getLikeCount(postId);
		return likesCount;
	}

	@GetMapping("/liked")
	@ResponseBody
	public String isPostLiked(@RequestParam Integer postId,
			@RequestParam Integer userId) {
		if (postService.isPostLiked(postId, userId)) {
			return "../../../fotos/profile/SelectedLike.png";
		} else {
			return "../../../fotos/profile/defaultLike.png";
		}
	}

}