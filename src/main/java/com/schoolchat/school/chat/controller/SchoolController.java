package com.schoolchat.school.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	@GetMapping({ "/react-frontend", "/react-frontend/**" })
	public String forwardReact() {
		return "react-frontend/public/index.html"; // ✅ correct
	}

	@GetMapping("/")
	public String searchSchoolByName(@ModelAttribute("region") String selectedRegionName, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (selectedRegionName == null || selectedRegionName.isEmpty()) {

			selectedRegionName = "bayern";

		}

		SchoolSearch schoolSearch = new SchoolSearch();
		// System.out.println("region: " + selectedRegionName);
		// System.out.println("selectedRegionName: " + jsonSchools.getJSON_FILE());

		session.setAttribute("jsonSchools", selectedRegionName);
		// model.addAttribute("JSON_file", jsonSchools);

		model.addAttribute("searchSchoolRequest", new SchoolModel());

		return "SearchSchool/searchSchoolSite";
	}

	@PostMapping("/")
	public String searchSchoolByNameAndPost(@ModelAttribute("searchSchoolRequest") SchoolModel schoolModel, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		if (session.getAttribute("jsonSchools") == null) {
			session.setAttribute("jsonSchools", "bayern");

		}
		System.out.println("Search School Work");

		System.out.println("jsonRegionValue: " + session.getAttribute("jsonSchools"));
		String jsonRegionValue = (String) session.getAttribute("jsonSchools");
		JSON_Schools jsonSchools = new JSON_Schools(jsonRegionValue);

		SchoolSearch schoolSearch = new SchoolSearch(jsonSchools.getJSON_List());
		// SchoolSearch schoolSearch = new SchoolSearch();

		// Видає всі школи як обєкт з даними
		// System.out.println("schools: " + schools);
		List<SchoolModel> schools = schoolSearch.getAllSchoolsByNameObjeckt(schoolModel.getName());

		model.addAttribute("schools", schools);
		// System.out.println("Search School: " + schools);
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
	public String foundSchoolData(
			@ModelAttribute("schoolData") String userCurrentSchoolModelData,
			Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// UserCurrentSchoolModel userCurrentSchoolModel = new UserCurrentSchoolModel();
		// Save current School in userCurrentSchoolModel
		UserCurrentSchoolModel userCurrentSchoolModel = new UserCurrentSchoolModel();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(userCurrentSchoolModelData);

			userCurrentSchoolModel.setofficial_id(jsonNode.get("official_id").asText());
			userCurrentSchoolModel.setId(jsonNode.get("id").asText());
			userCurrentSchoolModel.setName(jsonNode.get("name").asText());
			userCurrentSchoolModel.setSchoolType(jsonNode.get("schoolType").asText().isEmpty() ? "Unknown"
					: jsonNode.get("schoolType").asText());
			userCurrentSchoolModel.setAddress(jsonNode.get("address").asText());
			userCurrentSchoolModel.setState(jsonNode.get("state").asText());
			userCurrentSchoolModel.setPhone(jsonNode.get("phone").asText());
			userCurrentSchoolModel.setFax(jsonNode.get("fax").asText());
			userCurrentSchoolModel
					.setFullTimeSchool(Boolean.valueOf(jsonNode.get("fullTimeSchool").asText().isEmpty() ? "false"
							: jsonNode.get("fullTimeSchool").asText()));
			userCurrentSchoolModel.setLatitude(Double.parseDouble(jsonNode.get("latitude").asText()));
			userCurrentSchoolModel.setLongitude(Double.parseDouble(jsonNode.get("longitude").asText()));

		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception appropriately
		}

		redirectAttributes.addFlashAttribute("userCurrentSchoolModel", userCurrentSchoolModel);

		// Return current School
		System.out.println(userCurrentSchoolModel.toString());

		// System.out.println("foundSchoolData: "+userCurrentSchoolModel.toString());
		System.out.println();
		System.out.println("foundSchoolData: " + userCurrentSchoolModel);
		System.out.println();
		model.addAttribute("userCurrentSchoolModel", userCurrentSchoolModel);
		// System.out.println(userCurrentSchoolModel.toString());
		return "redirect:/register";

	}

}
