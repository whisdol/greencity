package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.dao.mapper.SpotMaintainActionRowMapper;
import de.whisdol.greencity.model.SpotMaintainAction;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 28.05.17.
 */
public class SpotMaintainActionDAO implements ISpotMaintainActionDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public SpotMaintainAction createSpotMaintainAction(SpotMaintainAction spotMaintainAction) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO spot_maintain_actions (spot_id, maintain_date, user_id, short_desc, description)" +
                "VALUES (?, ?, ?, ?, ?)",
                new Object[] {spotMaintainAction.getSpotId(), spotMaintainAction.getMaintainDate(),
                        spotMaintainAction.getUser().getId(), spotMaintainAction.getShortDescription(),
                        spotMaintainAction.getDescription()});
        return getActionByAction(spotMaintainAction);
    }

    @Override
    public SpotMaintainAction selectSpotMaintainActionById(long id) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        SpotMaintainAction action;
        try {
            action = (SpotMaintainAction) select.queryForObject("SELECT maintain_id, spot_id, maintain_date, user_id, short_desc, description FROM spot_maintain_actions WHERE maintain_id = ?",
                    new Object[]{ id },
                    new SpotMaintainActionRowMapper());
        } catch (Exception e) {
            // Query failed or returned not exactly one Object
            throw new ObjectNotFoundException("SpotMaintainAction", "ID: " + id);
        }
        return action;
    }

    @Override
    public List<SpotMaintainAction> getAllSpotMaintainActionsBySpot(long spotId) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return (List<SpotMaintainAction>) select.query("SELECT maintain_id, spot_id, maintain_date, user_id, short_desc, description FROM spot_maintain_actions WHERE spot_id = ?",
                new Object[]{ spotId },
                new SpotMaintainActionRowMapper());
    }

    @Override
    public void updateSpotMaintainAction(long id, SpotMaintainAction spotMaintainAction) {
        // Only updates the short & long description as these are the only values that the user should be able to adjust
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE spot_maintain_actions SET short_desc = ?, description = ? WHERE maintain_id = ?",
                new Object[] { spotMaintainAction.getShortDescription(), spotMaintainAction.getDescription() , id });
    }

    @Override
    public void deleteSpotMaintainAction(long id) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM spot_maintain_actions WHERE maintain_id = ? ",
                new Object[] { id });
    }

    @Override
    public boolean exists(SpotMaintainAction spotMaintainAction) {
        try {
            SpotMaintainAction confirmedSpotMaintainAction = getActionByAction(spotMaintainAction);
        } catch (ObjectNotFoundException e) {
            return false;
        }
        return true;
    }

    public SpotMaintainAction getActionByAction(SpotMaintainAction action) {
        JdbcTemplate exists = new JdbcTemplate(dataSource);
        List<SpotMaintainAction> match = exists.query("SELECT maintain_id, spot_id, maintain_date, user_id, short_desc, description FROM spot_maintain_actions WHERE spot_id = ? AND maintain_date = ?",
                new Object[] { action.getSpotId(), action.getMaintainDate() },
                new SpotMaintainActionRowMapper());
        if (match.size() == 0 ) {
            throw new ObjectNotFoundException("action", action.toString());
        }
        return match.get(0);
    }
}
