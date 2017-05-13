package de.whisdol.greencity.model;

import de.whisdol.greencity.dao.SpotDAO;

/**
 * Created by cedric on 22.04.17.
 */
public class Spot extends SpotDAO {
    private long id;
    private String name;
    private String description;
    private float size;
    private User owner;
    private Address address;

    public Spot(long id, String name, String description, float size, User owner, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.owner = owner;
        this.address = address;
    }

    public Spot() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setOwner(long userId) {
        this.owner = new User(userId);
    }

    public void setAddress(long addressId) {
        this.address = new Address(addressId);
    }
}
