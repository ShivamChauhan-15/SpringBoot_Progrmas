package com.example.StringResult;

import com.example.Comparison.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StringResultApplication {

	public static void main(String[] args) {
		SpringApplication.run(StringResultApplication.class, args);
		String s1="Hello";
		String s2="Bye";
		if(StringUtils.isEqual(s1,s2))
			System.out.println("Strings are equal");
		else
			System.out.println("Strings are not equal");
	}

}
