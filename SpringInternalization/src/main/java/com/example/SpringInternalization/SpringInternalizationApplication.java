package com.example.SpringInternalization;

import com.example.SpringInternalization.helper.UtilMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringInternalizationApplication {

	public static void main(String[] args) {
		UtilMap.create();
		SpringApplication.run(SpringInternalizationApplication.class, args);
	}

}
