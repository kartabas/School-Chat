package com.schoolchat.school.chat.controller;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.service.UsersService;
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


    @GetMapping("/")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest",new UsersModel());
        return "singUp";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new UsersModel());
        return "Login";
    }

    @PostMapping("/")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: "+ usersModel);
        UsersModel registeredUser= usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());

        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel ,Model model){
        System.out.println("login request: "+ usersModel);
        UsersModel authenticated= usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());

        model.addAttribute("userLogin",authenticated.getLogin());

        if(authenticated !=null){
            return "personal_page";
        }else{
            return "error_page";
        }
    }
}

