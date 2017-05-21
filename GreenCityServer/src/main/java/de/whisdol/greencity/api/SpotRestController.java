package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.SpotDAO;
import de.whisdol.greencity.model.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cedric on 22.04.17.
 */
@RestController
@RequestMapping(value = "/spots")
public class SpotRestController {
    private final SpotDAO spotDao;

    @Autowired
    SpotRestController() {
        this.spotDao = (SpotDAO) GreencityserverApplication.context.getBean("spotDAO");
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

}
