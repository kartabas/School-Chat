package com.schoolchat.school.chat.controller;




import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UsersService usersService;

    public HomeController(UsersService usersService) {
       this.usersService = usersService;
    }


    @GetMapping
    public String getHomePage(@ModelAttribute("userLogin") UsersModel usersModel,Model model ){
        System.out.println(usersModel);
       // System.out.println(usersModel.getCurrentSchoolData());

        model.addAttribute("userLogin",usersModel);

        return "personal_page";
    }


}
