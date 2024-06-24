package com.schoolchat.school.chat.controller;




import com.schoolchat.school.chat.Schools.SchoolModel;
import com.schoolchat.school.chat.model.UserCurrentSchoolModel;
import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.repository.SchoolSearch;
import com.schoolchat.school.chat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController  {

    @Autowired
    private UsersService usersService;

    public HomeController(UsersService usersService) {
       this.usersService = usersService;
    }


    @GetMapping
    public String getHomePage(@ModelAttribute("userLogin") UsersModel usersModel, Model model ){
        System.out.println(usersModel);

        SchoolSearch schoolSearch = new SchoolSearch();
        String  school = schoolSearch.getAllSchoolsByOfficialId(usersModel.getSchoolId()).toString();

        SchoolModel userSchoolModel = new SchoolModel(school);
        System.out.println("userSchoolModel:"+usersModel);

        model.addAttribute("userLogin",usersModel);
        model.addAttribute("userCurrentSchoolModel",userSchoolModel);
        return "home";
    }

    @GetMapping("{id}")
    public String getHomePageById(@ModelAttribute("userLogin") UsersModel usersModel, Model model ){
        //System.out.println(usersModel);
        //System.out.println(super.toString());

        model.addAttribute("userLogin",usersModel);

        return "redirect:home";
    }


}
