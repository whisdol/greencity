package de.whisdol.greencity.model;

/**
 * Created by cedric on 22.04.17.
 */
public class Spot {
    private long id;
    private String name;
    private String description;
    private float size;
    private int ranking;
    private int numberOfSpots;
    private float score;
    private User owner;
    private Address address;


    public Spot(long id, String name, String description, float size, User owner, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.owner = owner;
        this.address = address;

        // ToDo: Don't use static values
        this.ranking = 5;
        this.numberOfSpots = 34;
        this.score = 4.5f;
    }

    public Spot() {
        // ToDo: Don't use static values
        this.ranking = 5;
        this.numberOfSpots = 34;
        this.score = 4.5f;
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getNumberOfSpots() {
        return numberOfSpots;
    }

    public void setNumberOfSpots(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
