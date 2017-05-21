package de.whisdol.greencity.dao.mapper;

import de.whisdol.greencity.model.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cedric on 22.04.17.
 */
public class UserResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setUserRole(rs.getString("user_role"));
        user.setAvatar(rs.getLong("avatar_id"));
        user.setCity(rs.getLong("city_id"));
        user.setLastActivity(rs.getTimestamp("last_activity"));
        user.setCommentCount(rs.getInt("comment_count"));
        return user;
    }
}

