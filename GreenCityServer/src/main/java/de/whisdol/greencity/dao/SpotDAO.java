package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.dao.mapper.AddressRowMapper;
import de.whisdol.greencity.dao.mapper.SpotRowMapper;
import de.whisdol.greencity.model.Spot;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedri on 02.05.2017.
 */
public class SpotDAO implements ISpotDAO {
    DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public Spot createSpot(Spot spot) {
        return null;
    }

    @Override
    public Spot selectSpotById(long id) throws ObjectNotFoundException {
        new AddressRowMapper();
        return null;
    }

    @Override
    public Spot selectSpotByName(String name) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<Spot> selectAllSpots() {
        return null;
    }

    @Override
    public List<Spot> selectAllSpotsbyCity(long cityId) {
        return null;
    }

    @Override
    public void deleteSpot(long id) {

    }

    public Spot getSpotBySpot(Spot spot) {
        return null;
    }
}
