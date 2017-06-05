package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.UserDAO;
import de.whisdol.greencity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cedric on 22.04.17.
 */
@RestController
@RequestMapping(value = "/users")
public class UserRestController {
    private final UserDAO userDao;

    @Autowired
    UserRestController() {
        this.userDao = (UserDAO) GreencityserverApplication.context.getBean("userDAO");
    }

    @CrossOrigin
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    User getUser(@PathVariable long userId) {
        return userDao.selectUserById(userId);
    }

    @CrossOrigin
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteUser(@PathVariable long userId) {
        userDao.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createUser(@RequestBody User requestUser) {
        User user;
        try {
            user = userDao.getUserbyUser(requestUser);
        } catch (ObjectNotFoundException e) {
            user = userDao.createUser(requestUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body for an user " + requestUser + "\"}");
        }
        return ResponseEntity.ok(user);
    }


}
