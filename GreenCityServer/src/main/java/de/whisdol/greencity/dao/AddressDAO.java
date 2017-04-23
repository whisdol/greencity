package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.dao.mapper.AddressRowMapper;
import de.whisdol.greencity.model.Address;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public class AddressDAO implements IAddressDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public Address createAddress(Address address) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO address ( city_id, road_name, house_number, postcode, degree_of_latitude, degree_of_longitude ) VALUES ( ?, ?, ?, ?, ?, ?)",
                new Object[] { address.getCity().getId(), address.getRoadName(), address.getHouseNumber(), address.getPostCode(), address.getLatitude(), address.getLongitude() });
        return getAddressByAddress(address);
    }

    @Override
    public Address selectAddressById(long id) throws ObjectNotFoundException {
        List<Address> rs = selectAddressesById(id);
        if (rs.size() == 0) throw new ObjectNotFoundException("Address", Long.toString(id));
        return rs.get(0);
    }

    @Override
    public void updateAddress(long id, Address address) {
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE address SET city_id = ?, road_name = ?, house_number = ?, postcode = ?, degree_of_latitude = ?, degree_of_longitude = ? WHERE address_id = ?",
                new Object[] { address.getCity().getId(), address.getRoadName(), address.getHouseNumber(), address.getPostCode(), address.getLatitude(), address.getLongitude(), id });
    }

    @Override
    public void deleteAddress(long id) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM address WHERE address_id = ? ",
                new Object[] { id });
    }

    @Override
    public boolean exists(Address address) {
        try {
            Address confirmedAddress = getAddressByAddress(address);
        } catch (ObjectNotFoundException e) {
            return false;
        }
        return true;
    }

    private List<Address> selectAddressesById(long id) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT address_id, city_id, road_name, house_number, postcode, degree_of_latitude, degree_of_longitude FROM address WHERE address_id = ?",
                new Object[] { id },
                new AddressRowMapper());
    }

    public Address getAddressByAddress(Address address) {
        JdbcTemplate exists = new JdbcTemplate(dataSource);
        List<Address> match = exists.query("SELECT address_id, city_id, road_name, house_number, postcode, degree_of_latitude, degree_of_longitude FROM address WHERE city_id = ? AND road_name = ? AND house_number = ? AND postcode = ?",
                new Object[] { address.getCity().getId(), address.getRoadName(), address.getHouseNumber(), address.getPostCode() },
                new AddressRowMapper());

        if (match.size() == 0 ) {
            throw new ObjectNotFoundException("address", address.toString());
        }
        return match.get(0);
    }
}
