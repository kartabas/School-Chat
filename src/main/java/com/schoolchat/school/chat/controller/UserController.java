package com.schoolchat.school.chat.controller;


import com.schoolchat.school.chat.model.SchoolModel;
import com.schoolchat.school.chat.model.UserCurrentSchoolModel;
import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.service.UsersService;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController extends HttpServlet {

    @Autowired
    private UsersService usersService;


    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/register")
    public String getRegisterPage( Model model ,UserCurrentSchoolModel userCurrentSchoolModel, HttpServletRequest request ){
        HttpSession session = request.getSession();
        UsersModel usersModel = new UsersModel();


       // usersModel.setCurrentSchoolData(userCurrentSchoolModel);

       // System.out.println("getRegisterPage: "+userCurrentSchoolModel.toString());

        SchoolModel schoolModel = new SchoolModel(
                userCurrentSchoolModel.getOfficial_id(),
                userCurrentSchoolModel.getId(),
                userCurrentSchoolModel.getName(),
                userCurrentSchoolModel.getSchoolType(),
                userCurrentSchoolModel.getAddress(),
                userCurrentSchoolModel.getFullTimeSchool(),
                userCurrentSchoolModel.getState(),
                userCurrentSchoolModel.getPhone(),
                userCurrentSchoolModel.getFax(),
                userCurrentSchoolModel.getLatitude(),
                userCurrentSchoolModel.getLongitude()
        );



        if(userCurrentSchoolModel.getId() != null){
            session.setAttribute("userCurrentSchoolModel",userCurrentSchoolModel);
            model.addAttribute("userCurrentSchoolModel",userCurrentSchoolModel);

            usersModel.setSchoolId(userCurrentSchoolModel.getId());
            session.setAttribute("UserCurrentID",userCurrentSchoolModel.getId());
            System.out.println(schoolModel.toString());

        }else{
            model.addAttribute("userCurrentSchoolModel",session.getAttribute("userCurrentSchoolModel"));
            usersModel.setSchoolId((String) session.getAttribute("UserCurrentID"));
        }


        model.addAttribute("registerRequest",usersModel);

        return "singUp";
    }




    @GetMapping("/login")
    public String getLoginPage(Model model ){

        model.addAttribute("loginRequest",new UsersModel());

        return "Login";
    }




    @PostMapping("/register")
    public String register(@ModelAttribute  UserCurrentSchoolModel userCurrentSchoolModel ,UsersModel usersModel, Model model ){


        UsersModel registeredUser= usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail() ,usersModel.getSchoolId());

        return registeredUser == null ? "error_page" : "redirect:/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("usersModel") UsersModel usersModel ,RedirectAttributes redirectAttributes , Model model) {


        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());

        if (authenticated != null) {

            redirectAttributes.addFlashAttribute("userLogin", authenticated);
            return "redirect:home";
        } else {
            System.out.println("Authentication failed");
            return "error_page";
        }
    }



//old login post  api
    //    @PostMapping("/login")
//    public String login(@ModelAttribute UsersModel usersModel, Model model) {
//
//
//        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
//
//        if (authenticated != null) {
//
//            //return "redirect:/home";
//            model.addAttribute("userLogin",authenticated);
//            return "personal_page";
//        } else {
//            System.out.println("Authentication failed");
//            return "error_page";
//        }
//    }


}

