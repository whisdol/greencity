package de.whisdol.greencity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GreencityserverApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GreencityserverApplication.class, args);
		System.out.println("Test");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GreencityserverApplication.class);
	}

	@RequestMapping(value = "/")
	public String test(){
		return "Test";
	}
}
