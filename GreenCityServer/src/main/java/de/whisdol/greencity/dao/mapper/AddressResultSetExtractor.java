package de.whisdol.greencity.dao.mapper;

import de.whisdol.greencity.model.Address;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cedric on 22.04.17.
 */
public class AddressResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        Address address  = new Address();
        address.setId(rs.getLong("address_id"));
        address.setCity(rs.getLong("city_id"));
        address.setRoadName(rs.getString("road_name"));
        address.setHouseNumber(rs.getString("house_number"));
        address.setPostCode(rs.getString("postcode"));
        address.setLatitude(rs.getFloat("degree_of_latitude"));
        address.setLongitude(rs.getFloat("degree_of_longitude"));
        return address;
    }
}
