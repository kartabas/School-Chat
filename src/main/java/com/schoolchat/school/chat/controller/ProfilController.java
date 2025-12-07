package com.schoolchat.school.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.model.homeModels.ProfileModel;
import com.schoolchat.school.chat.model.homeModels.SchoolPostsModel;
import com.schoolchat.school.chat.model.schoolModels.SchoolModel;
import com.schoolchat.school.chat.repository.schoolRepository.SchoolSearch;
import com.schoolchat.school.chat.service.UsersService;
import com.schoolchat.school.chat.service.homeService.PostService;
import com.schoolchat.school.chat.service.homeService.ProfileService;
import com.schoolchat.school.chat.service.homeService.SchoolPostService;
import com.schoolchat.school.chat.service.likesService.LikeService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/profile")
public class ProfilController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Autowired
	private final UsersService usersService;

	@Autowired

	private final PostService postService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private SchoolPostService schoolPostService;

	@Autowired
	LikeService likeService;

	public ProfilController(UsersService usersService) {
		this.usersService = usersService;
		this.postService = new PostService();
	}

	@GetMapping
	public String getProfilePage(@ModelAttribute("userLogin") UsersModel usersModel, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {

			if (usersModel.getId() == null) {
				usersModel = (UsersModel) session.getAttribute("userLogin");

			}
			System.out.println();
			System.out.println("---------------ProfileController.java------------------------------");
			System.out.println(usersModel);
			System.out.println("-------------------------------------------------------------------");
			System.out.println();

			SchoolSearch schoolSearch = new SchoolSearch();
			String school = schoolSearch.getAllSchoolsByOfficialId(usersModel.getSchoolId()).toString();
			SchoolModel userSchoolModel = new SchoolModel(school);

			// System.out.println("userSchoolModel:"+usersModel);
			// System.out.println("Session ID: " + session.getId());

			model.addAttribute("userLogin", usersModel);
			model.addAttribute("userCurrentSchoolModel", userSchoolModel);
			model.addAttribute("posts", postService.getPostUserList(usersModel));

			// System.out.println();
			// System.out.println("Session for userLogin:
			// "+session.getAttribute("userLogin"));

			// System.out.println("Session for userCurrentSchoolModel:
			// "+session.getAttribute("userCurrentSchoolModel"));

			return "ProfilePage/profilePage";
		} else {
			return "redirect:/login";
		}

	}

	// @GetMapping("/profile/{id}")
	// public @ResponseBody UsersModel getProfilePageById(@PathVariable Integer id,
	// Model model, HttpServletRequest request, HttpServletResponse response) {
	// UsersModel usersModel = usersService.getUser(id);
	// return usersModel;

	// }

	@GetMapping("/usersposts")
	@ResponseBody
	public List<PostModel> getUserPostHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");
			return postService.getPostUserList(usersModel);
		}
		return null;
	}

	@GetMapping("/userinfo/{id}")
	@ResponseBody
	public String getAllUserPosts(@PathVariable Integer id) {

		UsersModel usersModel = usersService.getUser(id);
		return usersModel.getLogin();

	}

	@GetMapping("/profileinfoavatar")
	@ResponseBody
	public ProfileModel getProfileInfoAvatar(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");
			return (ProfileModel) profileService.getProfileByUserIModel(usersModel);
		}
		return null;
	}

	@PutMapping("/updateprofile")
	public String saveProfileData(@RequestBody ProfileModel profileModelData, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");

			ProfileModel profileModel = profileService.getUserModelByIdCheck(profileModelData);

			profileModel.setUsersModel(usersModel);
			profileModel.setProfileBiography(profileModelData.getProfileBiography());
			profileModel.setProfileBackground(profileModelData.getProfileBackground());
			profileModel.setProfileImage(profileModelData.getProfileImage());

			profileService.updateProfileModel(profileModel);

			session.setAttribute("profileModel", profileModel);
			return "redirect:/profile";
		}
		return "redirect:/profile";

	}

	@GetMapping("/{id}")
	@ResponseBody
	public ProfileModel getUserProfileData(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = usersService.getUser(id);
			ProfileModel profileModel = profileService.getProfileByUserIModel(usersModel);
			if (profileModel == null) {
				return new ProfileModel("../../../fotos/profile/userIcon.png",
						"../../../fotos/profile/web-application.png", " ");
			} else {
				return profileModel;
			}

		} else {
			return null;
		}

	}

	@Transactional
	@DeleteMapping("/deletepost/{id}")
	// @ResponseBody
	public String deletePost(@PathVariable Integer id) {
		PostModel postModel = postService.getPost(id);
		if (postModel == null) {
			return "redirect:/profile";
		}

		SchoolPostsModel schoolPostsModel = schoolPostService.getPostByPostModel(postModel);
		schoolPostService.deleteBySchoolPostId(schoolPostsModel.getSchoolPostId());

		likeService.deletePerPostId(id);

		postService.deleteByPostId(postModel.getPostId());
		System.out.println("Post was deleted: " + postModel.getPostId());
		return "redirect:/profile";
	}

	@PostMapping("/updatepost/{id}")
	public String updatePostDataOnProfilePage(@PathVariable Integer id, @RequestBody PostModel updataPostModel,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");
			PostModel postModel = postService.getPost(id);
			postModel.setMeassage(updataPostModel.getMeassage());
			postModel.setSendTime(updataPostModel.getSendTime());
			postModel.setPostImage(updataPostModel.getPostImage());

			postService.updatePostModel(postModel);
			System.out.println("Post was updated: " + postModel.getPostId());
			return "redirect:/profile";
		}

		return "redirect:/profile";
	}

}
