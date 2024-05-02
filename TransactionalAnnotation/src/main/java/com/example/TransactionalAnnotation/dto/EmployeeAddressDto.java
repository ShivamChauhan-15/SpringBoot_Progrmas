package com.example.TransactionalAnnotation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAddressDto {
    private String name;
    private int age;
    private String city;
    private String state;
}
