package com.example.SpringInterceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/")
    public String home(){
        System.out.println("This is Controller");
        return "home";

    }
    @RequestMapping("/login")
    public String login(){
        System.out.println("This is login Controller");
        return "login";
    }

}
