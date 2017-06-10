package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.SpotWorkEntry;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public interface ISpotWorkEntryDAO
{
    void setDataSource(DataSource ds);

    SpotWorkEntry createSpotWorkEntry(SpotWorkEntry spotWorkEntry);

    SpotWorkEntry selectSpotWorkEntryById(long id) throws ObjectNotFoundException;

    List<SpotWorkEntry> getAllSpotWorkEntriesBySpot(long spotId) throws ObjectNotFoundException;

    void updateSpotWorkEntry(long id, SpotWorkEntry spotWorkEntry);

    void deleteSpotWorkEntry(long id);

    boolean exists(SpotWorkEntry spotWorkEntry);



}
