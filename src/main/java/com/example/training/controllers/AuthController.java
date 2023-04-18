package com.example.training.controllers;

import com.example.training.entities.AuthRequest;
import com.example.training.jwtutility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtility jwtUtility;

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest)throws  Exception{
        try {
            System.out.println("inside generate token");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
            System.out.println("inside generate token before exception");
        }catch (Exception e){
            System.out.println("inside excecption generate token");
            throw new Exception("Invalid username and password !!");
        }
        return jwtUtility.generateToken(authRequest.getUsername());
    }
}

