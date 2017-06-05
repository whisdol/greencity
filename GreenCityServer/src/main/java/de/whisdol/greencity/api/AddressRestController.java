package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.AddressDAO;
import de.whisdol.greencity.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cedric on 22.04.17.
 */
@RestController
@RequestMapping(value = "/addresses")
public class AddressRestController {
    private final AddressDAO addressDao;

    @Autowired
    AddressRestController() {
        this.addressDao = (AddressDAO) GreencityserverApplication.context.getBean("addressDAO");
    }

    @CrossOrigin
    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    Address getAddress(@PathVariable long addressId) {
        return addressDao.selectAddressById(addressId);
    }

    @CrossOrigin
    @RequestMapping(value = "/{addressId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> updateAddress(@PathVariable long addressId, @RequestBody Address address) {
        addressDao.updateAddress(addressId, address);
        return ResponseEntity.ok(addressDao.selectAddressById(addressId));
    }

    @CrossOrigin
    @RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteAddress(@PathVariable long addressId) {
        addressDao.deleteAddress(addressId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createAddress(@RequestBody Address requestAddress) {
        Address address;
        try {
            address = addressDao.getAddressByAddress(requestAddress);
        } catch (ObjectNotFoundException e) {
            address = addressDao.createAddress(requestAddress);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body for an Address " + requestAddress + "\"}");
        }
        return ResponseEntity.ok(address);
    }



}
