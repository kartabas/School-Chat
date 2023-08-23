package com.schoolchat.school.chat.service;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public UsersModel registerUser(String login ,String password ,String email){
        if (login == null || password == null) {
            return  null;
        } else {

            if(usersRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }


            UsersModel usersModel = new UsersModel();

            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return  usersRepository.save(usersModel);
        }


    }

    public UsersModel authenticate(String login ,String password){
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);

    }
}
