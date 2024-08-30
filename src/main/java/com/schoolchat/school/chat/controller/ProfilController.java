package com.schoolchat.school.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.schoolModels.SchoolModel;
import com.schoolchat.school.chat.repository.schoolRepository.SchoolSearch;
import com.schoolchat.school.chat.service.UsersService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProfilController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Autowired
	private final UsersService usersService;

	public ProfilController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping("/profile")
	public String getProfilePage(@ModelAttribute("userLogin") UsersModel usersModel, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {

			if (usersModel.getId() == null) {
				usersModel = (UsersModel) session.getAttribute("userLogin");

			}

			System.out.println(usersModel);

			SchoolSearch schoolSearch = new SchoolSearch();
			String school = schoolSearch.getAllSchoolsByOfficialId(usersModel.getSchoolId()).toString();
			SchoolModel userSchoolModel = new SchoolModel(school);

			// System.out.println("userSchoolModel:"+usersModel);
			// System.out.println("Session ID: " + session.getId());

			model.addAttribute("userLogin", usersModel);
			model.addAttribute("userCurrentSchoolModel", userSchoolModel);

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


	@GetMapping("/profile/{id}")
	public @ResponseBody UsersModel getProfilePageById(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		UsersModel usersModel = usersService.getUser(id);
		return usersModel;
	
	}





}
