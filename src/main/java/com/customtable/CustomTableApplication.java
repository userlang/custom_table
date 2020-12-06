package com.customtable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.customtable.common",
		"com.customtable.mapper",
		"com.customtable.service",
		"com.customtable.api"
		 })
public class CustomTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomTableApplication.class, args);
	}


}
