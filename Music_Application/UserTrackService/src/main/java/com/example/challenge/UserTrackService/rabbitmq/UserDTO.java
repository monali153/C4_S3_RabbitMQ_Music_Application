package com.example.challenge.UserTrackService.rabbitmq;

public class UserDTO {

    private int userId;
    private String password;

    public UserDTO() {
    }

    public UserDTO(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
