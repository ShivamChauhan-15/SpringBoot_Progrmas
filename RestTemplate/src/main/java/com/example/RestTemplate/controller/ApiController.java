package com.example.RestTemplate.controller;

import com.example.RestTemplate.dto.ApiDto;
import com.example.RestTemplate.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/fetch")
    public String fetchData() {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/200"; // Example API endpoint
        return apiService.fetchDataFromApi(apiUrl);
    }
    @PutMapping("/update")
    public String updateData() {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/200"; // Example API endpoint
        String title = "Updated Title";
        String body = "Updated Body";
        return apiService.updateDataToApi(apiUrl, title, body);
    }

    @PostMapping("/create")
    public String createPost(@RequestBody ApiDto apiDto) {
        String apiUrl = "http://localhost:8092/create"; // Example API endpoint for creating posts
        return apiService.create(apiUrl,apiDto);
    }
}
