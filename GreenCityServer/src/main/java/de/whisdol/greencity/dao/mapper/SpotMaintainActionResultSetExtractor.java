package de.whisdol.greencity.dao.mapper;

import de.whisdol.greencity.model.Address;
import de.whisdol.greencity.model.SpotMaintainAction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cedric on 22.04.17.
 */
public class SpotMaintainActionResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        SpotMaintainAction action  = new SpotMaintainAction();
        action.setId(rs.getLong("maintain_id"));
        action.setSpotId(rs.getLong("spot_id"));
        action.setMaintainDate(rs.getTimestamp("maintain_date"));
        action.setUser(rs.getLong("user_id"));
        action.setDescription(rs.getString("description"));
        action.setShortDescription(rs.getString("short_desc"));
        return action;
    }
}
