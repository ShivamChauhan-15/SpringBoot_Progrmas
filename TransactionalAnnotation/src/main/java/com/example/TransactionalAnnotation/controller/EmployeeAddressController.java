package com.example.TransactionalAnnotation.controller;

import com.example.TransactionalAnnotation.service.AddressService;
import com.example.TransactionalAnnotation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeAddressController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AddressService addressService;
    @GetMapping("/addEmployee")
    public String addEmployee(){
        employeeService.addEmployee();
        return "Employee added successfully";
    }
    @GetMapping("/updateAge/{id}")
    public int updateAge(@PathVariable String id){
        int age = employeeService.updateAge(id);
        return age;
    }

    @GetMapping("/getAge/{id}")
    public int getAge(@PathVariable String id){
        int age = employeeService.getAge(id);
        return age;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id){
        return employeeService.remove(id);
    }

    @GetMapping("/fetchData")
    public ResponseEntity<?> fetchData(){
        List<?> list = employeeService.fetchData();
        return ResponseEntity.ok(list);

    }
}
