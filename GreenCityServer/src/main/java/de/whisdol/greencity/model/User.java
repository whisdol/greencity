package de.whisdol.greencity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by cedric on 22.04.17.
 */
public class User {
    private long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String userRole;
    private City city;
    private LocalDateTime lastActivity;
    private int commentCount;

    public User(long id, String userName, String password, String userRole, City city, LocalDateTime lastActivity, int commentCount) {

        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.city = city;
        this.lastActivity = lastActivity;
        this.commentCount = commentCount;
    }

    public User() {

    }

    public User(String userName, String password, City city) {
        this.id = -1;
        this.userName = userName;
        this.password = password;
        this.city = city;
        this.userRole = "Grüner Daumen";
        this.lastActivity = LocalDateTime.now();
        this.commentCount = 0;
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

    public String getPassword() {
        return password;
    }

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

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

}
