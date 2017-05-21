package de.whisdol.greencity.dao.mapper;

import de.whisdol.greencity.model.Image;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cedric on 22.04.17.
 */
public class ImageResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setId(rs.getLong("image_id"));
        image.setName(rs.getString("image_name"));
        image.setFileDir(rs.getString("file_dir"));
        image.setType(rs.getString("image_type"));
        return image;
    }
}

