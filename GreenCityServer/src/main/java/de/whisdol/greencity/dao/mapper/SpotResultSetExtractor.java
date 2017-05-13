package de.whisdol.greencity.dao.mapper;

import de.whisdol.greencity.model.Spot;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cedric on 22.04.17.
 */
public class SpotResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        Spot spot = new Spot();
        spot.setId(rs.getLong("spot_id"));
        spot.setName(rs.getString("spot_name"));
        spot.setDescription(rs.getString("spot_description"));
        spot.setSize(rs.getFloat("spot_size_qm"));
        spot.setOwner(rs.getLong("owner_user_id"));
        spot.setAddress(rs.getLong("address_id"));
        return spot;
    }
}

