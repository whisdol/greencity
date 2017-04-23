package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.City;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public interface ICityDAO
{
    void setDataSource(DataSource ds);

    void createCity(String name);

    void createCity(City city);

    City selectCityById(long id) throws ObjectNotFoundException;

    City selectCityByName(String Name) throws ObjectNotFoundException;

    List<City> selectAllCities();

    void deleteAllCities();

    void deleteCity(City city);

}
