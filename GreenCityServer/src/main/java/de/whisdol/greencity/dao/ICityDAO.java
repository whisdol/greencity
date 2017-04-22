package de.whisdol.greencity.dao;

import de.whisdol.greencity.model.City;
import javassist.NotFoundException;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public interface ICityDAO
{
    void setDataSource(DataSource ds);

    void createCity(String name);

    City selectCityById(long id);

    List<City> selectCitiesById(long id);

    List<City> selectAllCities();

    void deleteAllCities();

    void deleteCity(City city);

}
