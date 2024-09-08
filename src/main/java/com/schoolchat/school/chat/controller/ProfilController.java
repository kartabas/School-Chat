package com.schoolchat.school.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.model.homeModels.ProfileModel;
import com.schoolchat.school.chat.model.schoolModels.SchoolModel;
import com.schoolchat.school.chat.repository.schoolRepository.SchoolSearch;
import com.schoolchat.school.chat.service.UsersService;
import com.schoolchat.school.chat.service.homeService.PostService;
import com.schoolchat.school.chat.service.homeService.ProfileService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
			// if (profileModelData.getProfileBackground() == null || profileModelData.getProfileBackground().length() == 0) {
			// 	ProfileModel profileModelBackground = (ProfileModel) session.getAttribute("profileModel");
			// 	profileModel.setProfileBackground(profileModelBackground.getProfileBackground());

			// }

			// if (profileModelData.getProfileImage() == null || profileModelData.getProfileImage().length() == 0) {
			// 	ProfileModel profileModelImage = (ProfileModel) session.getAttribute("profileModel");
			// 	profileModel.setProfileImage(profileModelImage.getProfileImage());

			// }

			// if (profileModelData.getProfileBiography() == null || profileModelData.getProfileBiography().length() == 0) {
			// 	ProfileModel profileModelBiography = (ProfileModel) session.getAttribute("profileModel");
			// 	profileModel.setProfileBiography(profileModelBiography.getProfileBiography());
			// }

			// if (profileModelData != null) {
				profileModel.setUsersModel(usersModel);
				profileModel.setProfileBiography(profileModelData.getProfileBiography());
				profileModel.setProfileBackground(profileModelData.getProfileBackground());
				profileModel.setProfileImage(profileModelData.getProfileImage());
			// }

			profileService.updateProfileModel(profileModel);

			// ProfileModel profileModel = new ProfileModel();
			// profileModel.setUsersModel(usersModel);
			// profileModel.setProfileBiography(profileModelData.getProfileBiography());
			// profileModel.setProfileBackground(profileModelData.getProfileBackground());
			// profileModel.setProfileImage(profileModelData.getProfileImage());

			// profileService.savProfileModel(profileModel);

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

			return profileModel;
		} else {
			return null;
		}

	}

}
