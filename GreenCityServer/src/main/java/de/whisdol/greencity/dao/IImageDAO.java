package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.Image;

import javax.sql.DataSource;

/**
 * Created by cedri on 29.04.2017.
 */
public interface IImageDAO {
    void setDataSource(DataSource ds);

    Image createImage(Image image);

    Image selectImageById(long id) throws ObjectNotFoundException;

    void deleteImageById(long id);
}
