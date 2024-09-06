package com.schoolchat.school.chat.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.model.homeModels.SchoolPostsModel;
import com.schoolchat.school.chat.model.schoolModels.SchoolModel;
import com.schoolchat.school.chat.repository.schoolRepository.SchoolSearch;
import com.schoolchat.school.chat.service.UsersService;
import com.schoolchat.school.chat.service.homeService.PostService;
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

			System.out.println(usersModel);

			SchoolSearch schoolSearch = new SchoolSearch();
			String school = schoolSearch.getAllSchoolsByOfficialId(usersModel.getSchoolId()).toString();
			SchoolModel userSchoolModel = new SchoolModel(school);

			System.out.println("userSchoolModel:" + usersModel);
			System.out.println("Session ID: " + session.getId());

			model.addAttribute("userLogin", usersModel);
			model.addAttribute("userCurrentSchoolModel", userSchoolModel);

			session.setAttribute("school", school);
			session.setAttribute("userId", usersModel.getId());
			session.setAttribute("userLogin", usersModel);
			session.setAttribute("userCurrentSchoolModel", userSchoolModel);
			System.out.println();
			System.out.println("Session for userLogin: " + session.getAttribute("userLogin"));

			System.out.println("Session for userCurrentSchoolModel: " + session.getAttribute("userCurrentSchoolModel"));
			System.out.println("Replay COUNT: " + count);
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
	// public List<SchoolPostsModel> getAllPostsPerSchool(HttpServletRequest request, HttpServletResponse response) {

	// 	HttpSession session = request.getSession();
	// 	UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");
	// 	return schoolPostService.getAllSchoolPosts(usersModel.getSchoolId());
	// }

	@GetMapping("/allposts")
	@ResponseBody	
	public List<PostModel> getAllPostsPerSchool(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UsersModel usersModel = (UsersModel) session.getAttribute("userLogin");
		return postService.getAllSchoolPosts(usersModel.getSchoolId());
	}



	@GetMapping("/error")
	public String getErrorPage() {
		return "error_page";
	}

}
