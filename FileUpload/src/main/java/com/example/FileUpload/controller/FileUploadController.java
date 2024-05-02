package com.example.FileUpload.controller;

import com.example.FileUpload.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {
    @Autowired
    private FileUploadHelper fileUploadHelper;
    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            //validation
            if (file.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide file to upload");
            //to check content of file
            if (!file.getContentType().equals("image/jpeg"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPEG content type are allowed");

            boolean f = fileUploadHelper.uploadFile(file);
            if (f) {
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong please try again!");
    }
}
