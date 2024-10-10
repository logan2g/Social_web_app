package com.intprog.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class ProjectApplication {
	public static Long userId;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
