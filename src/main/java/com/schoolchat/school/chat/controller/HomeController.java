package com.schoolchat.school.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.model.homeModels.ProfileModel;
import com.schoolchat.school.chat.model.schoolModels.SchoolModel;
import com.schoolchat.school.chat.repository.schoolRepository.SchoolSearch;
import com.schoolchat.school.chat.service.UsersService;
import com.schoolchat.school.chat.service.homeService.PostService;
import com.schoolchat.school.chat.service.homeService.ProfileService;
import com.schoolchat.school.chat.service.homeService.SchoolPostService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private final UsersService usersService;

	@Autowired
	private final PostService postService;

	@Autowired
	private SchoolPostService schoolPostService;

	@Autowired
	private ProfileService profileService;

	private Integer count = 0;

	public HomeController(PostService postService, UsersService usersService) {
		this.usersService = usersService;
		this.postService = new PostService();
	}

	@GetMapping
	public String getHomePage(@ModelAttribute("userLogin") UsersModel usersModel, Model model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		count += 1;

		if (session != null) {

			if (usersModel.getId() == null) {
				usersModel = (UsersModel) session.getAttribute("userLogin");

			}

			System.out.println("HomeController usersModel:  " + usersModel);

			SchoolSearch schoolSearch = new SchoolSearch();
			SchoolModel userSchoolModel = new SchoolModel(
					schoolSearch.getAllSchoolsByOfficialId(usersModel.getSchoolId()).get(0));

			System.out.println();
			System.out.println("School from HomeController: " + userSchoolModel);
			System.out.println();

			System.out.println();
			System.out.println("userSchoolModel:" + usersModel);
			System.out.println("Session ID: " + session.getId());
			System.out.println();

			model.addAttribute("userLogin", usersModel);
			model.addAttribute("userCurrentSchoolModel", userSchoolModel);

			// session.setAttribute("school", school);
			session.setAttribute("userId", usersModel.getId());
			session.setAttribute("userLogin", usersModel);
			session.setAttribute("userCurrentSchoolModel", userSchoolModel);

			System.out.println();
			System.out.println("Session for userLogin: " + session.getAttribute("userLogin"));

			System.out.println("Session for userCurrentSchoolModel: " + session.getAttribute("userCurrentSchoolModel"));
			System.out.println("Replay COUNT: " + count);
			System.out.println();

			
			// ProfileModel profileModel = new ProfileModel();
			// profileModel = profileService.getProfileByUserIModel(usersModel);
			// if (profileModel != null) {
			// model.addAttribute("profileModel", profileModel);
			// } else {
			// model.addAttribute("profileModel", new ProfileModel());
			// }

			return "home";
		} else {
			return "redirect:/login";
		}

	}

	@GetMapping("/{id}")
	public String getHomePageById(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		UsersModel usersModel = usersService.getUser(id);
		redirectAttributes.addFlashAttribute("userLogin", usersModel);

		return "redirect:/home";
	}

	// @GetMapping("/allposts")
	// @ResponseBody
	// public List<SchoolPostsModel> getAllPostsPerSchool(HttpServletRequest
	// request, HttpServletResponse response) {

	// HttpSession session = request.getSession();
	// UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");
	// return schoolPostService.getAllSchoolPosts(usersModel.getSchoolId());
	// }

	@GetMapping("/allpostsperschool")
	@ResponseBody
	public List<PostModel> getAllPostsPerSchool(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");

		// List<PostModel> sortedPosts =
		// postService.getAllSchoolPosts(usersModel.getSchoolId()).stream()
		// .sorted(Comparator.comparing(PostModel::getSendTime).reversed())
		// .collect(Collectors.toList());

		// System.out.println("All posts per school (HomeController): " + sortedPosts);

		return postService.getAllSchoolPosts(usersModel.getSchoolId());
		// return sortedPosts;

	}

	@GetMapping("/alluserposts/{id}")
	@ResponseBody
	public String getAllUserPosts(@PathVariable Integer id) {
		UsersModel usersModel = usersService.getUser(id);
		return usersModel.getLogin();

	}

	@PostMapping("/savepost")
	public String savePostMain(@RequestBody PostModel sendPostModel, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("savePostMain: " + sendPostModel.getMeassage());
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");

			PostModel postModel = postService.saveUserPost(usersModel.getSchoolId(), usersModel,
					sendPostModel.getMeassage(), sendPostModel.getSendTime(), sendPostModel.getPostImage());

			schoolPostService.savePostInUserSchool(usersModel.getSchoolId(), postModel, usersModel);
			System.out.println("Post save in database!!!");

			return "redirect:/home";
		} else {
			return "error_page";
		}
	}

	@GetMapping("/profileinfo/{id}")
	@ResponseBody
	public String getUserProfileData(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			UsersModel usersModel = usersService.getUser(id);
			ProfileModel profileModel = profileService.getProfileByUserIModel(usersModel);
			if (profileModel == null) {
				return null;
			} else {
				return profileModel.getProfileImage();
			}

		} else {
			return null;
		}

	}

	@GetMapping("/error")
	public String getErrorPage() {
		return "error_page";
	}

}
