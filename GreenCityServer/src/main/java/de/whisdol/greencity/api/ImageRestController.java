package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.ImageDAO;
import de.whisdol.greencity.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cedric on 22.04.17.
 */
@RestController
@RequestMapping(value = "/images")
public class ImageRestController {
    private final ImageDAO imageDao;

    @Autowired
    ImageRestController() {
        this.imageDao = (ImageDAO) GreencityserverApplication.context.getBean("imageDAO");
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    Image getImage(@PathVariable long imageId) {
        return imageDao.selectImageById(imageId);
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteImage(@PathVariable long imageId) {
        imageDao.deleteImageById(imageId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createImage(@RequestBody Image requestImage) {
        Image image;
        try {
            image = imageDao.getImageByImage(requestImage);
        } catch (ObjectNotFoundException e) {
            image = imageDao.createImage(requestImage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body for an Image " + requestImage + "\"}");
        }
        return ResponseEntity.ok(image);
    }


}
