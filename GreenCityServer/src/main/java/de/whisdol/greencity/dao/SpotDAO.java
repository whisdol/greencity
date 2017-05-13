package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.dao.mapper.SpotRowMapper;
import de.whisdol.greencity.model.Spot;
import org.springframework.jdbc.core.JdbcTemplate;

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
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO spots ( spot_name, address_id, owner_user_id, spot_size_qm, spot_description) VALUES ( ?, ?, ?, ?, ?)",
                new Object[] { spot.getName(), spot.getAddress().getId(), spot.getOwner().getId(), spot.getSize(), spot.getDescription() });
        return getSpotBySpot(spot);
    }

    @Override
    public Spot selectSpotById(long id) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        Spot spot;
        try {
            spot = (Spot) select.queryForObject("SELECT spot_id, spot_name, address_id, owner_user_id, spot_size_qm, spot_description FROM spots WHERE spot_id = ?",
                    new Object[]{ id },
                    new SpotRowMapper());
        } catch (Exception e) {
            // Query failed or returned not exactly one Object
            throw new ObjectNotFoundException("Spot", "Id: " + id);
        }
        return spot;
    }

    @Override
    public Spot selectSpotByName(String name) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        Spot spot;
        try {
            spot = (Spot) select.queryForObject("SELECT spot_id, spot_name, address_id, owner_user_id, spot_size_qm, spot_description FROM spots WHERE spot_name = ?",
                    new Object[]{name},
                    new SpotRowMapper());
        } catch (Exception e) {
            // Query failed or returned not exactly one Object
            throw new ObjectNotFoundException("Spot", "Name: " + name);
        }
        return spot;
    }

    @Override
    public List<Spot> selectAllSpots() {
        JdbcTemplate selectAll = new JdbcTemplate(dataSource);
        return selectAll.query("SELECT spot_id, spot_name, address_id, owner_user_id, spot_size_qm, spot_description FROM spots",
                new SpotRowMapper());
    }

    @Override
    public List<Spot> selectAllSpotsByCity(long cityId) {
        JdbcTemplate selectAll = new JdbcTemplate(dataSource);
        return selectAll.query("SELECT spot_id, spot_name, spots.address_id, owner_user_id, spot_size_qm, spot_description FROM spots INNER JOIN address ON address.address_id = spots.address_id WHERE address.city_id = ?",
                new Object[] { cityId },
                new SpotRowMapper());
    }

    @Override
    public void deleteSpot(long id) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM spots WHERE spot_id = ?",
                new Object[] { id });

    }

    public Spot getSpotBySpot(Spot spot) {
        return selectSpotByName(spot.getName());
    }
}
