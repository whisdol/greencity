package de.whisdol.greencity.dao;

import de.whisdol.greencity.dao.mapper.CityRowMapper;
import de.whisdol.greencity.model.City;
import javassist.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public class GreenCityCityDAO implements ICityDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public void createCity(String name) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO city (name) VALUES (?)", new Object[] { name });
    }

    @Override
    public List<City> selectCitiesById(long id) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT city_id, city_name FROM city WHERE city_id = ?",
                        new Object[] { id },
                        new CityRowMapper());
    }

    @Override
    public City selectCityById(long id) {
        List<City> rs = selectCitiesById(id);
        if (rs.size() == 0) return new City();
        return rs.get(0);
    }

    @Override
    public List<City> selectAllCities() {
        JdbcTemplate selectAll = new JdbcTemplate(dataSource);
        return selectAll.query("SELECT city_id, city_name FROM city", new CityRowMapper());
    }

    @Override
    public void deleteCity(City city) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM city WHERE city_id = ? AND city_name = ?", new Object[] { city.getId(), city.getName() });
    }

    @Override
    public void deleteAllCities() {
        JdbcTemplate deleteAll = new JdbcTemplate(dataSource);
        deleteAll.update("DELETE FROM city");
    }
}
