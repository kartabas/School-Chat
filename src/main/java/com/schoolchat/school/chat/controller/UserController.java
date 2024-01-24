package com.schoolchat.school.chat.controller;

import com.schoolchat.school.chat.Schools.SchoolModel;
import com.schoolchat.school.chat.model.UserCurrentSchoolModel;
import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.service.UsersService;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller

public class UserController {

    @Autowired
    private UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/register")
    public String getRegisterPage( Model model ){


        model.addAttribute("registerRequest",new UsersModel());
        return "singUp";
    }




    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new UsersModel());
        return "Login";
    }




    @PostMapping("/register")
    public String register(UsersModel usersModel, Model model ){



        System.out.println("register request: "+ usersModel);
        UsersModel registeredUser= usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());

        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel ,Model model ){
        System.out.println("login request: "+ usersModel);
        UsersModel authenticated= usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());


        model.addAttribute("userLogin",authenticated);

        if(authenticated != null){

            return "redirect:/home";

        }else{
           return "error_page";
        }
    }



}

