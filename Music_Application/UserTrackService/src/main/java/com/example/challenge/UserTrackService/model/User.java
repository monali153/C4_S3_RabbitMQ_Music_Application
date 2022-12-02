package com.example.challenge.UserTrackService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private int userId;
    private String userName;

    private String password;
    private List<Track> trackList;

    public User() {
    }

    public User(int userId, String userName, String password, List<Track> trackList) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.trackList = trackList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", trackList=" + trackList +
                '}';
    }
}
