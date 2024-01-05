package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.Schools.SchoolModel;
import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @Autowired
    private UsersService usersService;

    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/home")
    public String homePage(@ModelAttribute UsersModel usersModel , Model model , SchoolModel schoolModel){
        UsersModel authenticated= usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());

        model.addAttribute("userSchool",schoolModel);
        model.addAttribute("userLogin",authenticated);

        return "personal_page";
    }

}
