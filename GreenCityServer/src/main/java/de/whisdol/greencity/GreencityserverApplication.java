package de.whisdol.greencity;

import de.whisdol.greencity.dao.GreenCityCityDAO;
import de.whisdol.greencity.model.City;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class GreencityserverApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GreencityserverApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GreencityserverApplication.class);
	}

	@RequestMapping(value = "/")
	public String test(){

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		GreenCityCityDAO dao = (GreenCityCityDAO) context.getBean("cityDAO");;

		List<City> cities = dao.selectAllCities();

		return Arrays.toString(cities.stream().toArray());
	}
}
