package com.schoolchat.school.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.schoolModels.SchoolModel;
import com.schoolchat.school.chat.model.schoolModels.UserCurrentSchoolModel;
import com.schoolchat.school.chat.repository.schoolRepository.SchoolSearch;
import com.schoolchat.school.chat.schoolService.JSON_Schools;
import com.schoolchat.school.chat.service.UsersService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SchoolController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsersModel usersModel;
	@Autowired
	private UsersService usersService;

	@GetMapping("/")
	public String searchSchoolByName(@ModelAttribute("region") String selectedRegionName, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (selectedRegionName == null || selectedRegionName.isEmpty()) {

			selectedRegionName = "bayern";
			
		}
		JSON_Schools jsonSchools = new JSON_Schools(selectedRegionName);

		SchoolSearch schoolSearch = new SchoolSearch();
		System.out.println("region: " + selectedRegionName);
		System.out.println("selectedRegionName: " + jsonSchools.getJSON_FILE());

		session.setAttribute("jsonSchools", jsonSchools.getJSON_FILE());
		model.addAttribute("JSON_file", jsonSchools);

		model.addAttribute("searchSchoolRequest", new SchoolModel());

		return "SearchSchool/searchSchoolSite";
	}

	@PostMapping("/")
	public String searchSchoolByNameAndPost(@ModelAttribute("searchSchoolRequest") SchoolModel schoolModel, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		System.out.println("Search School Work");
		System.out.println("jsonRegionValue: " + session.getAttribute("jsonSchools"));
		// TODO fix this code
		SchoolSearch schoolSearch = new SchoolSearch((String) session.getAttribute("jsonSchools"));
		// SchoolSearch schoolSearch = new SchoolSearch();

		// Видає всі школи як обєкт з даними
		// System.out.println("schools: " + schools);
		List<SchoolModel> schools = schoolSearch.getAllSchoolsByNameObjeckt(schoolModel.getName());

		model.addAttribute("schools", schools);

		return "SearchSchool/searchSchoolSite";
		
	}

	@GetMapping("/user/{id}")
	public String getHomePageById(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		UsersModel usersModel = usersService.getUser(id);

		redirectAttributes.addFlashAttribute("userLogin", usersModel);

		return "redirect:/home";
	}

	@GetMapping("/app")
	public String foundSchool(Model model) {
		SchoolModel currentschoolModel = new SchoolModel();

		model.addAttribute("schoolData", currentschoolModel);

		return "SearchSchool/searchSchoolSite";
	}

	@PostMapping("/app")
	public String foundSchoolData(@ModelAttribute("schoolData") UserCurrentSchoolModel userCurrentSchoolModel,
			Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// Save current School in userCurrentSchoolModel
		userCurrentSchoolModel.setCurrentUserSchool(userCurrentSchoolModel.getCurrentSchool());

		redirectAttributes.addFlashAttribute("userCurrentSchoolModel", userCurrentSchoolModel);
		// Return current School
		// System.out.println(userCurrentSchoolModel.getCurrentSchool());

		// System.out.println("foundSchoolData: "+userCurrentSchoolModel.toString());

		model.addAttribute("userCurrentSchoolModel", userCurrentSchoolModel);
		// System.out.println(userCurrentSchoolModel.toString());
		return "redirect:/register";

	}

}
