package com.example.TransactionalAnnotation;

import com.example.TransactionalAnnotation.model.Employee;
import com.example.TransactionalAnnotation.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TransactionalAnnotationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TransactionalAnnotationApplication.class, args);
	}

}
