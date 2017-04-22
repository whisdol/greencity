package de.whisdol.greencity.model;

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
