package de.whisdol.greencity.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by cedric on 22.04.17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String object, String id) {
        super("could not find " + object + " '" + id + "'.");
    }
}
