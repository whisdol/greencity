package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.dao.mapper.ImageRowMapper;
import de.whisdol.greencity.model.Image;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.JDBCType;
import java.util.List;

/**
 * Created by cedric on 29.04.2017.
 */
public class ImageDAO implements IImageDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public Image createImage(Image image) {
        // ToDo: Implement saving file to disk
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO images (image_name, image_type, file_dir) VALUES (?, ?, ?)",
                new Object[]{image.getName(), image.getType(), image.getFileDir()});
        return getImageByImage(image);
    }

    @Override
    public Image selectImageById(long id) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        Image image;
        try {
            image = (Image) select.queryForObject("SELECT image_id, image_type, file_dir, image_name FROM images WHERE image_id = ?",
                    new Object[]{id},
                    new ImageRowMapper());
        } catch (Exception e) {
            // Query failed or returned not exactly one Object
            throw new ObjectNotFoundException("Image", "ID: " + Long.toString(id));
        }
        return image;
    }

    @Override
    public void deleteImageById(long id) {
        //ToDo: Delete file from file system
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM images WHERE image_id = ?",
                new Object[]{id});
    }

    public List<Image> getImagesBySpotId(long spotId) {
        JdbcTemplate join = new JdbcTemplate(dataSource);
        return join.query("SELECT spot_id, spot_images.image_id, image_type, file_dir, image_name FROM spot_images INNER JOIN images ON spot_images.image_id = images.image_id WHERE spot_id = ?",
                new Object[] {spotId},
                new ImageRowMapper());
    }

    public void addImageToSpot(long spotId, long imageId) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO spot_images (spot_id, image_id) VALUES (?, ?)",
                new Object[] {spotId, imageId});

    }

    public Image getImageByImage(Image image) {
        JdbcTemplate exists = new JdbcTemplate(dataSource);
        List<Image> match = exists.query("SELECT image_id, image_type, file_dir, image_name FROM images WHERE image_name = ? AND file_dir = ?",
                new Object[]{image.getName(), image.getFileDir()},
                new ImageRowMapper());

        if (match.size() == 0) {
            throw new ObjectNotFoundException("Image", image.toString());
        }
        return match.get(0);

    }
}
