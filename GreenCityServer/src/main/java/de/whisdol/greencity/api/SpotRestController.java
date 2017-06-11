package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.CityDAO;
import de.whisdol.greencity.dao.ImageDAO;
import de.whisdol.greencity.dao.SpotDAO;
import de.whisdol.greencity.model.Image;
import de.whisdol.greencity.model.Spot;
import jdk.nashorn.internal.ir.CatchNode;
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
    private final ImageDAO imageDao;

    @Autowired
    SpotRestController() {
        this.spotDao = (SpotDAO) GreencityserverApplication.context.getBean("spotDAO");
        this.cityDao = (CityDAO) GreencityserverApplication.context.getBean("cityDAO");
        this.imageDao = (ImageDAO) GreencityserverApplication.context.getBean("imageDAO");
    }

    @CrossOrigin
    @RequestMapping(value = "/{spotId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    Spot getSpot(@PathVariable long spotId) {
        return spotDao.selectSpotById(spotId);
    }

    @CrossOrigin
    @RequestMapping(value = "/{spotId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteSpot(@PathVariable long spotId) {
        spotDao.deleteSpot(spotId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping(value = "/search", params = "cityId", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> searchSpotsById(@RequestParam long cityId) {
        return searchSpots(cityId);
    }

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping(value = "/{spotId}/images", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getSpotImages(@PathVariable long spotId) {
        try {

        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(imageDao.getImagesBySpotId(spotId));
    }

    @CrossOrigin
    @PostMapping(value = "/{spotId}/images/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> addImageToSpot(@PathVariable long spotId, @RequestBody Image requestImage) {
        long imageId = requestImage.getId();
        Spot spot;
        Image verifiedImage;
        try {
            spot = spotDao.selectSpotById(spotId);
            verifiedImage = imageDao.selectImageById(imageId);
        } catch (ObjectNotFoundException e) {
            // Either the Spot or the Image does not exist
            return ResponseEntity.badRequest().body("{ \"error\": \"" + e.getMessage() + "\"}");
        }
        try {
            imageDao.addImageToSpot(spotId, imageId);
        } catch (Exception e) {
            // The insert failed
            return ResponseEntity.badRequest().body("{ \"error\": \"Image with ID " + imageId +
                    " has already been added to Spot with ID " + spotId + "\"}");
        }
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> searchSpots(long cityId) {
        List<Spot> spots = spotDao.selectAllSpotsByCity(cityId);
        if (spots.size() < 1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spots);
    }
}
