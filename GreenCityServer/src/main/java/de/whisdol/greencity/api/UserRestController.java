package de.whisdol.greencity.api;

import de.whisdol.greencity.dao.CityDAO;
import de.whisdol.greencity.model.City;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
@RestController
@RequestMapping("/cities")
public class UserRestController {

    private final CityDAO cityDao;

    @Autowired
    UserRestController() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        this.cityDao = (CityDAO) context.getBean("cityDAO");
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    List<City> getAllCities() {
        return cityDao.selectAllCities();
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    City getCity(@PathVariable Long cityId) {
        return cityDao.selectCityById(cityId);
    }

    /**
     * Creates a City with a name or returns the existing city.
     * @param request { "name": "Name of the City" }
     * @return The City Object
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createCity(@RequestBody String request) {
        String name = "";
        try {
            JSONObject json = new JSONObject(request);
            name = json.getString("name");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body. Should be: { \\\"name\\\": \\\"Name of the City\\\" }\" }");
        }

        City city;
        try {
            city = cityDao.selectCityByName(name);
            return ResponseEntity.ok(city);
        } catch (ObjectNotFoundException e ) {
            cityDao.createCity(name);
            return ResponseEntity.ok(cityDao.selectCityByName(name));
        }
    }
}
