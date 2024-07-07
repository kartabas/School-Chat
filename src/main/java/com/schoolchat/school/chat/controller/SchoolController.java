package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.Schools.JSON_Schools;

import com.schoolchat.school.chat.Schools.SchoolModel;
import com.schoolchat.school.chat.model.UserCurrentSchoolModel;
import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.repository.SchoolSearch;
import com.schoolchat.school.chat.service.UsersService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class SchoolController extends HttpServlet {
//    private  UsersRepository usersRepository;
    private static final long serialVersionUID = 1L;
    private UsersModel usersModel;
    @Autowired
    private UsersService usersService;


    @GetMapping("/")
    public String searchSchoolByName(Model model, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        JSON_Schools jsonSchools = new JSON_Schools();
        SchoolSearch schoolSearch =new SchoolSearch();

        model.addAttribute("JSON_file",jsonSchools.JSON_FILE);
        model.addAttribute("searchSchoolRequest",new SchoolModel());

        return "SearchSchool/searchSchoolSite";
    }

    @GetMapping("/api")
    public String setIdUser(){
        return "redirect:/api/1";
    }


    @PostMapping("/")
    public String searchSchoolByNameAndPost(@ModelAttribute("searchSchoolRequest") SchoolModel schoolModel, Model model ) {

        System.out.println("Search School Work");
        SchoolSearch schoolSearch = new SchoolSearch();

        // Видає всі школи як обєкт з даними
        //System.out.println("schools: " + schools);
        List<SchoolModel> schools =schoolSearch.getAllSchoolsByNameObjeckt(schoolModel.getName());

        model.addAttribute("schools", schools);

        return "SearchSchool/searchSchoolSite";

    }


    @GetMapping("/user/{id}")
    public String getHomePageById(@PathVariable Integer id, Model model , RedirectAttributes redirectAttributes){
        UsersModel usersModel = usersService.getUser(id);

        redirectAttributes.addFlashAttribute("userLogin", usersModel);

        return "redirect:/home";
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

       // System.out.println("foundSchoolData: "+userCurrentSchoolModel.toString());



        model.addAttribute("userCurrentSchoolModel",userCurrentSchoolModel);
       // System.out.println(userCurrentSchoolModel.toString());
        return "redirect:/register";

    }

}
