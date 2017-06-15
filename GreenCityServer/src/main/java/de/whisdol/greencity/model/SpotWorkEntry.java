package de.whisdol.greencity.model;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.SpotWorkEntryDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by cedric on 28.05.17.
 */
public class SpotWorkEntry {
    private long id;
    private long spotId; //Returning all Spot data is not useful here
    private Timestamp entryDate;
    private User user;
    private String shortDescription;
    private String description;

    public SpotWorkEntry() {
        this.id = -1;
        this.spotId = -1;
        this.entryDate = Timestamp.valueOf(LocalDateTime.now());
        this.user = new User();
        this.shortDescription = "Short Description";
        this.description = "Long Description";
    }

    public SpotWorkEntry(long spotId, Timestamp entryDate, User user, String shortDescription, String description) {
        this.id = -1;
        this.spotId = spotId;
        this.entryDate = entryDate;
        this.user = user;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public SpotWorkEntry(long spotId, User user, String shortDescription, String description) {
        this.id = -1;
        this.spotId = spotId;
        this.user = user;
        this.shortDescription = shortDescription;
        this.description = description;
        this.entryDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public SpotWorkEntry(long id) {
        this.id = id;
        SpotWorkEntryDAO actionDAO = (SpotWorkEntryDAO) GreencityserverApplication.context.getBean("spotWorkEntryDAO");
        SpotWorkEntry dbAction = actionDAO.selectSpotWorkEntryById(id);
        this.spotId = dbAction.spotId;
        this.entryDate = dbAction.entryDate;
        this.user = dbAction.user;
        this.shortDescription = dbAction.shortDescription;
        this.description = dbAction.description;
    }

    public SpotWorkEntry(long id, long spotId, Timestamp entryDate, User user, String shortDescription, String description) {
        this.id = id;
        this.spotId = spotId;
        this.entryDate = entryDate;
        this.user = user;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSpotId() {
        return spotId;
    }

    public void setSpotId(long spotId) {
        this.spotId = spotId;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(long userId) {
        this.user = new User(userId);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
