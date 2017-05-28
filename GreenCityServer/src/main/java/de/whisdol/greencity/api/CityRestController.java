package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.CityDAO;
import de.whisdol.greencity.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
@RestController
@RequestMapping("/cities")
public class CityRestController {

    private final CityDAO cityDao;

    @Autowired
    CityRestController() {
        this.cityDao = (CityDAO) GreencityserverApplication.context.getBean("cityDAO");
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    List<City> getAllCities() {
        return cityDao.selectAllCities();
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    City getCity(@PathVariable long cityId) {
        return cityDao.selectCityById(cityId);
    }

    /**
     * Creates a City with a name or returns the existing city.
     * @param requestCity { "name": "Name of the City" }
     * @return The City Object
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createCity(@RequestBody City requestCity) {

        if (requestCity.getName() == "" || requestCity.getName() == null) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body. Should be: { \\\"name\\\": \\\"Name of the City\\\" }\" }");
        }

        City city;
        try {
            city = cityDao.selectCityByName(requestCity.getName());
            return ResponseEntity.ok(city);
        } catch (ObjectNotFoundException e ) {
            cityDao.createCity(requestCity.getName());
            return ResponseEntity.ok(cityDao.selectCityByName(requestCity.getName()));
        }
    }
}
