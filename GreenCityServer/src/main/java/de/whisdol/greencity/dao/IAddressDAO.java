package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.Address;
import de.whisdol.greencity.model.City;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public interface IAddressDAO
{
    void setDataSource(DataSource ds);

    Address createAddress(Address address);

    Address selectAddressById(long id) throws ObjectNotFoundException;

    void updateAddress(long id, Address address);

    void deleteAddress(long id);

    boolean exists(Address address);



}
