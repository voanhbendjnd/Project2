package com.javaweb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.javaweb")
//@SpringBootApplication(scanBasePackages = {"com.javaweb.api", "repository.entity", "serviceimplement"})
//@SpringBootApplication(scanBasePackages = {
//	    "com.javaweb.api",
//	    "com.javaweb.beans",
//	    "com.javaweb.config",
//	    "com.javaweb.utils",
//	    "com.javaweb",
//	    "repository",
//	    "repository.entity",
//	    "repository.implement.copy",
//	    "service",
//	    "serviceimplement"
//	})
//@SpringBootApplication(scanBasePackages = {"com.javaweb.api", "repository"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
