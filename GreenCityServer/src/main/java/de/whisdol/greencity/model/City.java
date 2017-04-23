package de.whisdol.greencity.model;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.CityDAO;

/**
 * Created by cedric on 22.04.17.
 */
public class City {
    private long id;
    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(City city) {
        this.id = city.id;
        this.name = city.name;
    }

    public City(long id) {
        this.id = id;
        CityDAO cityDao =(CityDAO) GreencityserverApplication.context.getBean("cityDAO");
        this.name = cityDao.selectCityById(id).name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City ID: " + id +
                "\nCity Name: " + name;
    }
}
