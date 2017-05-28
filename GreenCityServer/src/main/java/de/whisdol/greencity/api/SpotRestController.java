package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.CityDAO;
import de.whisdol.greencity.dao.SpotDAO;
import de.whisdol.greencity.model.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
@RestController
@RequestMapping(value = "/spots")
public class SpotRestController {
    private final SpotDAO spotDao;
    private final CityDAO cityDao;

    @Autowired
    SpotRestController() {
        this.spotDao = (SpotDAO) GreencityserverApplication.context.getBean("spotDAO");
        this.cityDao = (CityDAO) GreencityserverApplication.context.getBean("cityDAO");
    }

    @RequestMapping(value = "/{spotId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    Spot getSpot(@PathVariable long spotId) {
        return spotDao.selectSpotById(spotId);
    }

    @RequestMapping(value = "/{spotId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteSpot(@PathVariable long spotId) {
        spotDao.deleteSpot(spotId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createImage(@RequestBody Spot requestSpot) {
        Spot spot;
        try {
            spot = spotDao.getSpotBySpot(requestSpot);
        } catch (ObjectNotFoundException e) {
            spot = spotDao.createSpot(requestSpot);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body for a spot " + requestSpot + "\"}");
        }
        return ResponseEntity.ok(spot);
    }

    ResponseEntity<?> addWorkLogEntry(@PathVariable long spotId, @RequestBody String spotComment) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/search", params = "cityId", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> searchSpotsById(@RequestParam long cityId) {
        return searchSpots(cityId);
    }

    @GetMapping(value = "/search", params = "cityName", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> searchSpotsByName(@RequestParam String cityName) {
        long cityId = -1;
        try {
            cityId = cityDao.selectCityByName(cityName).getId();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return searchSpots(cityId);
    }


    private ResponseEntity<?> searchSpots(long cityId) {
        List<Spot> spots = spotDao.selectAllSpotsByCity(cityId);
        if (spots.size() < 1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spots);
    }
}
