package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.SpotMaintainAction;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public interface ISpotMaintainActionDAO
{
    void setDataSource(DataSource ds);

    SpotMaintainAction createSpotMaintainAction(SpotMaintainAction spotMaintainAction);

    SpotMaintainAction selectSpotMaintainActionById(long id) throws ObjectNotFoundException;

    List<SpotMaintainAction> getAllSpotMaintainActionsBySpot(long spotId) throws ObjectNotFoundException;

    void updateSpotMaintainAction(long id, SpotMaintainAction spotMaintainAction);

    void deleteSpotMaintainAction(long id);

    boolean exists(SpotMaintainAction spotMaintainAction);



}
