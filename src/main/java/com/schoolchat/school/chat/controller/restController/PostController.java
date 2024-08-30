package com.schoolchat.school.chat.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.service.homeService.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	// @GetMapping("/post")
	// public ResponseEntity<List<PostModel>> getAllPosts() {
	// List<PostModel> posts = postService.getAllPosts();
	// return ResponseEntity.ok(posts);
	// }

	// @GetMapping("/post")
	// public Optional<PostModel> getAllPosts() {

	// return postService.getPosts();
	// }

	@GetMapping("/{id}")
	public PostModel getUserbyId(@PathVariable Integer id) {
		PostModel post = postService.getPost(id);
		System.out.println(post);
		return post;

	}

}
