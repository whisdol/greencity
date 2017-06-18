package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.dao.mapper.SpotWorkEntryRowMapper;
import de.whisdol.greencity.model.SpotWorkEntry;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 28.05.17.
 */
public class SpotWorkEntryDAO implements ISpotWorkEntryDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public SpotWorkEntry createSpotWorkEntry(SpotWorkEntry spotWorkEntry) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO spot_work_entries (spot_id, maintain_date, user_id, short_desc, description)" +
                "VALUES (?, ?, ?, ?, ?)",
                new Object[] {spotWorkEntry.getSpotId(), spotWorkEntry.getEntryDate(),
                        spotWorkEntry.getUser().getId(), spotWorkEntry.getShortDescription(),
                        spotWorkEntry.getDescription()});
        return getEntryByEntry(spotWorkEntry);
    }

    @Override
    public SpotWorkEntry selectSpotWorkEntryById(long id) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        SpotWorkEntry entry;
        try {
            entry = (SpotWorkEntry) select.queryForObject("SELECT maintain_id, spot_id, maintain_date, user_id, short_desc, description FROM spot_work_entries WHERE maintain_id = ?",
                    new Object[]{ id },
                    new SpotWorkEntryRowMapper());
        } catch (Exception e) {
            // Query failed or returned not exactly one Object
            throw new ObjectNotFoundException("SpotWorkEntry", "ID: " + id);
        }
        return entry;
    }

    @Override
    public List<SpotWorkEntry> getAllSpotWorkEntriesBySpot(long spotId) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return (List<SpotWorkEntry>) select.query("SELECT maintain_id, spot_id, maintain_date, user_id, short_desc, description FROM spot_work_entries WHERE spot_id = ? ORDER BY maintain_date DESC",
                new Object[]{ spotId },
                new SpotWorkEntryRowMapper());
    }

    @Override
    public void updateSpotWorkEntry(long id, SpotWorkEntry spotWorkEntry) {
        // Only updates the short & long description as these are the only values that the user should be able to adjust
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE spot_work_entries SET short_desc = ?, description = ? WHERE maintain_id = ?",
                new Object[] { spotWorkEntry.getShortDescription(), spotWorkEntry.getDescription() , id });
    }

    @Override
    public void deleteSpotWorkEntry(long id) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM spot_work_entries WHERE maintain_id = ? ",
                new Object[] { id });
    }

    @Override
    public boolean exists(SpotWorkEntry spotWorkEntry) {
        try {
            SpotWorkEntry confirmedSpotWorkEntry = getEntryByEntry(spotWorkEntry);
        } catch (ObjectNotFoundException e) {
            return false;
        }
        return true;
    }

    public SpotWorkEntry getEntryByEntry(SpotWorkEntry entry) {
        JdbcTemplate exists = new JdbcTemplate(dataSource);
        List<SpotWorkEntry> match = exists.query("SELECT maintain_id, spot_id, maintain_date, user_id, short_desc, description FROM spot_work_entries WHERE spot_id = ? AND maintain_date = ?",
                new Object[] { entry.getSpotId(), entry.getEntryDate() },
                new SpotWorkEntryRowMapper());
        if (match.size() == 0 ) {
            throw new ObjectNotFoundException("entry", entry.toString());
        }
        return match.get(0);
    }
}
