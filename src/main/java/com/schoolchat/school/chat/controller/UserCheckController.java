package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCheckController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/api/{id}")
    public UsersModel getUserbyId(@PathVariable Integer id){
        UsersModel user = usersService.getUser(id);

        return user;

    }









}
