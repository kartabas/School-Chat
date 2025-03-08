package com.schoolchat.school.chat.controller.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.service.homeService.PostService;
import com.schoolchat.school.chat.service.homeService.SchoolPostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private SchoolPostService schoolPostService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	// @GetMapping("/post")
	// public ResponseEntity<List<PostModel>> getAllPosts() {
	// List<PostModel> posts = postService.getAllPosts();
	// return ResponseEntity.ok(posts);
	// }

	// Save post in database for user
	@GetMapping
	public String getUserbyId(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");

			PostModel postModel = postService.saveUserPost(usersModel.getSchoolId(), usersModel, "Img", "22:38", "LOL");

			schoolPostService.savePostInUserSchool(usersModel.getSchoolId(), postModel, usersModel);
			System.out.println("Post save in database!!!");

			return "redirect:/home";
		} else {
			return "error_page";
		}
	}

	// @GetMapping("/{id}")
	// public PostModel getUserbyId(@PathVariable Integer id) {
	// PostModel post = postService.getPost(id);
	// System.out.println(post);
	// return post;
	// }

	// TODO: Update post make to end
	@PutMapping("/updatepost/{id}")
	public String updatePostData(@PathVariable Integer id, @RequestBody PostModel postModel, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");

			PostModel updatedpostModel = postService.getPost(id);

			updatedpostModel.setMeassage(postModel.getMeassage());
			updatedpostModel.setSendTime(postModel.getSendTime());
			updatedpostModel.setPostImage(postModel.getPostImage());

			postService.updatePostModel(updatedpostModel);

			session.setAttribute("profileModel", updatedpostModel);
			return "redirect:/profile";
		}
		return "redirect:/profile";

	}



	//TODO: Delete post test
	@DeleteMapping("/deletepost/{id}")
	@ResponseBody
	public String deletePost(@PathVariable Integer id) {
		PostModel postModel = postService.getPost(id);
		postService.deleteByPostId(postModel.getPostId());
		return "redirect:/profile";
	}

	// Show all post of user
	@GetMapping("/usersposts")
	@ResponseBody
	public List<PostModel> getUserPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");
			return postService.getPostUserList(usersModel);
		}
		return null;
	}

	@GetMapping("/allposts")
	@ResponseBody
	public List<PostModel> getAllPosts() {

		return (List<PostModel>) postService.getAllPosts();
	}

}
