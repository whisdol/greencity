package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.Spot;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public interface ISpotDAO
{
    void setDataSource(DataSource ds);

    Spot createSpot(Spot spot);

    Spot selectSpotById(long id) throws ObjectNotFoundException;

    Spot selectSpotByName(String name) throws ObjectNotFoundException;

    List<Spot> selectAllSpots();

    List<Spot> selectAllSpotsbyCity(long cityId);

    void deleteSpot(long id);

}
