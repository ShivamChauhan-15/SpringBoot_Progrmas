package com.example.RestTemplate.service;

import com.example.RestTemplate.dto.ApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String fetchDataFromApi(String apiUrl) {
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//        String response = restTemplate.getForObject(apiUrl, String.class);
//        return response;
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return "Error: Unable to fetch data from the API";
        }

    }
    public String updateDataToApi(String apiUrl, String title, String body) {
        String requestData = String.format("{\"title\": \"%s\", \"body\": \"%s\"}", title, body);
        restTemplate.put(apiUrl, requestData);
        return "Data updated successfully!";
    }

    public String create(String apiUrl,ApiDto apiDto){
        try {
            // Make a POST request to create a new post
            String createdPost = restTemplate.postForObject(apiUrl, apiDto, String.class);
            return "New post created: " + createdPost;
        } catch (Exception e) {
            // If there's an error, return a 500 Internal Server Error response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create post", e);
        }
    }
}

