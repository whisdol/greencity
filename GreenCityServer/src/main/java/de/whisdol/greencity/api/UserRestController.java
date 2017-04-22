package de.whisdol.greencity.api;

import de.whisdol.greencity.dao.GreenCityCityDAO;
import de.whisdol.greencity.model.City;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
@SpringBootApplication
@RestController
@RequestMapping("/city")
public class UserRestController {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<City> getAllCities() {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        GreenCityCityDAO dao = (GreenCityCityDAO) context.getBean("cityDAO");;

        List<City> cities = dao.selectAllCities();
        return cities;
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public City getCity(@PathVariable Long cityId) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        GreenCityCityDAO dao = (GreenCityCityDAO) context.getBean("cityDAO");;
        return dao.selectCityById(cityId);
    }
}
