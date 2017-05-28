package de.whisdol.greencity.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.whisdol.greencity.model.City;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * Created by cedric on 22.04.17.
 */
public class CityResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        City city  = new City();
        city.setId(rs.getLong("city_id"));
        city.setName(rs.getString("city_name"));
        return city;
    }
}

