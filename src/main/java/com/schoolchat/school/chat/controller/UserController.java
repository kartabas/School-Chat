package com.schoolchat.school.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class UserController {

    @GetMapping("/")
    public String getRegisterPage(){

        return "singUp";
    }

    @GetMapping("/login")
    public String getLoginPage(){

        return "Login";
    }

}

