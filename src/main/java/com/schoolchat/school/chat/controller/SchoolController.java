package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.Schools.JSON_Schools;

import com.schoolchat.school.chat.Schools.SchoolModel;
import com.schoolchat.school.chat.Schools.SchoolSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class SchoolController {


    @GetMapping("/login/search")
    public String searchSchoolByName(Model model){
        JSON_Schools jsonSchools = new JSON_Schools();
        SchoolSearch schoolSearch =new SchoolSearch();

        model.addAttribute("JSON_file",jsonSchools.JSON_FILE);
        model.addAttribute("searchSchoolRequest",new SchoolModel());

        return "SearchSchool/searchSchoolSite";
    }



    @PostMapping("/login/search")
    public String searchSchoolByPost(@ModelAttribute("searchSchoolRequest") SchoolModel schoolModel, Model model) {
        System.out.println("searchSchoolRequest.name: " + schoolModel.getName());
        System.out.println("searchSchoolRequest.Id: " + schoolModel.getId());
        SchoolSearch schoolSearch = new SchoolSearch();
//Видає всі перевірені ім'я
//        List<String> schools = schoolSearch.getAllSchoolsByName(schoolModel.getName());

// Видає всі школи як обєкт з даними
        List<SchoolModel> schools =schoolSearch.getAllSchoolsByNameObjeckt(schoolModel.getName());

        model.addAttribute("schools", schools);
        System.out.println("schools: " + schools);



        return "SearchSchool/searchSchoolSite";

    }




}
