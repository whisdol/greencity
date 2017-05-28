package de.whisdol.greencity.model;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.SpotMaintainActionDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by cedric on 28.05.17.
 */
public class SpotMaintainAction {
    private long id;
    private long spotId; //Returning all Spot data is not useful here
    private Timestamp maintainDate;
    private User user;
    private String shortDescription;
    private String description;

    public SpotMaintainAction() {
        this.id = -1;
        this.spotId = -1;
        this.maintainDate = Timestamp.valueOf(LocalDateTime.now());
        this.user = new User();
        this.shortDescription = "Short Description";
        this.description = "Long Description";
    }

    public SpotMaintainAction(long spotId, Timestamp maintainDate, User user, String shortDescription, String description) {
        this.id = -1;
        this.spotId = spotId;
        this.maintainDate = maintainDate;
        this.user = user;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public SpotMaintainAction(long spotId, User user, String shortDescription, String description) {
        this.id = -1;
        this.spotId = spotId;
        this.user = user;
        this.shortDescription = shortDescription;
        this.description = description;
        this.maintainDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public SpotMaintainAction(long id) {
        this.id = id;
        SpotMaintainActionDAO actionDAO = (SpotMaintainActionDAO) GreencityserverApplication.context.getBean("spotMaintainActionDAO");
        SpotMaintainAction dbAction = actionDAO.selectSpotMaintainActionById(id);
        this.spotId = dbAction.spotId;
        this.maintainDate = dbAction.maintainDate;
        this.user = dbAction.user;
        this.shortDescription = dbAction.shortDescription;
        this.description = dbAction.description;
    }

    public SpotMaintainAction(long id, long spotId, Timestamp maintainDate, User user, String shortDescription, String description) {
        this.id = id;
        this.spotId = spotId;
        this.maintainDate = maintainDate;
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

    public Timestamp getMaintainDate() {
        return maintainDate;
    }

    public void setMaintainDate(Timestamp maintainDate) {
        this.maintainDate = maintainDate;
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
