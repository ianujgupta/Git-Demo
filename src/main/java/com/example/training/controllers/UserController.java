package com.example.training.controllers;

import com.example.training.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class UserController {

    @GetMapping("/index")
    public String showLoginPage(){
        return "index";
    }
//
//    @GetMapping("/")
//    public String LoginPage(){
//        return "login";
//    }
}
