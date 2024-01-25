package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.Schools.JSON_Schools;

import com.schoolchat.school.chat.Schools.SchoolModel;
import com.schoolchat.school.chat.model.UserCurrentSchoolModel;
import com.schoolchat.school.chat.repository.SchoolSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class SchoolController {


    @GetMapping("/")
    public String searchSchoolByName(Model model){
        JSON_Schools jsonSchools = new JSON_Schools();
        SchoolSearch schoolSearch =new SchoolSearch();

        model.addAttribute("JSON_file",jsonSchools.JSON_FILE);
        model.addAttribute("searchSchoolRequest",new SchoolModel());

        return "SearchSchool/searchSchoolSite";
    }



    @PostMapping("/")
    public String searchSchoolByNameAndPost(@ModelAttribute("searchSchoolRequest") SchoolModel schoolModel, Model model) {
        System.out.println("Search School Work");
        SchoolSearch schoolSearch = new SchoolSearch();

        // Видає всі школи як обєкт з даними
        //System.out.println("schools: " + schools);
        List<SchoolModel> schools =schoolSearch.getAllSchoolsByNameObjeckt(schoolModel.getName());

        model.addAttribute("schools", schools);

        return "SearchSchool/searchSchoolSite";

    }




    @GetMapping("/app")
    public String foundSchool(Model model){
        SchoolModel currentschoolModel =new SchoolModel();


        model.addAttribute("schoolData",currentschoolModel);


        return "SearchSchool/searchSchoolSite";
    }

    @PostMapping("/app")
    public String foundSchoolData(@ModelAttribute("schoolData") UserCurrentSchoolModel userCurrentSchoolModel, Model model, RedirectAttributes redirectAttributes) {
        //Save current School in userCurrentSchoolModel
        userCurrentSchoolModel.setCurrentUserSchool(userCurrentSchoolModel.getCurrentSchool());

        redirectAttributes.addFlashAttribute("userCurrentSchoolModel", userCurrentSchoolModel);
        // Return current School
        // System.out.println(userCurrentSchoolModel.getCurrentSchool());

        model.addAttribute("userCurrentSchoolModel",userCurrentSchoolModel);
       // System.out.println(userCurrentSchoolModel.toString());
        return "redirect:/register";

    }

}
