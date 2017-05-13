package de.whisdol.greencity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.UserDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by cedric on 22.04.17.
 */
public class User {
    private long id;
    private String userName;
    private String password;
    private String userRole;
    private City city;
    private Timestamp lastActivity;
    private int commentCount;
    private Image avatar;

    public User(long id, String userName, String password, String userRole, City city, Timestamp lastActivity, int commentCount, Image avatar) {

        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.city = city;
        this.lastActivity = lastActivity;
        this.commentCount = commentCount;
        this.avatar = avatar;
    }

    public User() {
        this.id = -1;
        this.userName = "tobeset";
        this.password = "tobeset";
        this.userRole = "Grüner Daumen";
        this.lastActivity = Timestamp.valueOf(LocalDateTime.now());
        this.commentCount = 0;
    }

    public User(String userName, String password, City city, Image avatar) {
        this.id = -1;
        this.userName = userName;
        this.password = password;
        this.city = city;
        this.userRole = "Grüner Daumen";
        this.lastActivity = Timestamp.valueOf(LocalDateTime.now());
        this.commentCount = 0;
        this.avatar = avatar;
    }



    public User(long userId) {
        this.id = userId;
        UserDAO userDao = (UserDAO) GreencityserverApplication.context.getBean("userDAO");
        User dbUser = userDao.selectUserById(userId);
        this.userName = dbUser.userName;
        this.password = dbUser.password;
        this.userRole = dbUser.userRole;
        this.city = dbUser.city;
        this.lastActivity = dbUser.lastActivity;
        this.commentCount = dbUser.commentCount;
        this.avatar = dbUser.avatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Timestamp getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Timestamp lastActivity) {
        this.lastActivity = lastActivity;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public void setCity(long cityId) {
        this.city = new City(cityId);
    }

    public void setAvatar(long avatarId) {
        this.avatar = new Image(avatarId);
    }
}
