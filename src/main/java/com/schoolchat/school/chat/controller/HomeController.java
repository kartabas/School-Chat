package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.Schools.SchoolModel;
import com.schoolchat.school.chat.model.UserCurrentSchoolModel;
import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.service.UsersService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UsersService usersService;

    public HomeController(UsersService usersService) {
       this.usersService = usersService;
    }


//    @GetMapping
//    public String getHomePage(@ModelAttribute  UsersModel authenticated ,UserCurrentSchoolModel userCurrentSchoolModel,Model model ){
//        System.out.println(authenticated);
//        model.addAttribute("userCurrentSchoolModel",userCurrentSchoolModel);
//
//        return "personal_page";
//
//
//    }

//    @PostMapping
//    public String homePage(@ModelAttribute  UsersModel usersModel ,Model model ){
//
//
//        return "personal_page";
//
//    }

}
