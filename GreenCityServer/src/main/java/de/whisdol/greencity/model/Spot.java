package de.whisdol.greencity.model;

/**
 * Created by cedric on 22.04.17.
 */
public class Spot {
    private int id;
    private String name;
    private String description;
    private int size;
    private User owner;
    private Address address;

    public Spot(int id, String name, String description, int size, User owner, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.owner = owner;
        this.address = address;
    }

    public int getId() {
        return id;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
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
}
