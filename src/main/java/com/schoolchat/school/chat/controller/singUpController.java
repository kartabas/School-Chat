package com.schoolchat.school.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class singUpController {

    @GetMapping("/")
    public String singUp(){
        return "singUp";
    }
}
