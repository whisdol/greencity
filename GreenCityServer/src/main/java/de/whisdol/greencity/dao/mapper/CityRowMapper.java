package de.whisdol.greencity.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cedric on 22.04.17.
 */
public class CityRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        CityResultSetExtractor extractor = new CityResultSetExtractor();
        return extractor.extractData(resultSet);
    }
}
