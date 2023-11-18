package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.Schools.JSON_test;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class SchoolController {


    @GetMapping("/login/search")
    public String getSearchPage(Model model){
        JSON_test jsonTest = new JSON_test();
        List<JSONObject> schools = jsonTest.JSON_test();


        model.addAttribute("schools",schools);

        return "SearchSchool/searchSchoolSite";
    }



   /*
   @PostMapping("/login/search")
    public String SearchPage(@ModelAttribute School school, Model model){

        System.out.println("login request: "+ school);
        return "SearchSchool/searchSchoolSite";
    }
    */
}
