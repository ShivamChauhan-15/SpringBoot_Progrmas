package com.example.ExtractDetailsFromTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
    @RequestMapping("/upload")
    public String fileUpload(){
        return "fileUpload";
    }
}
