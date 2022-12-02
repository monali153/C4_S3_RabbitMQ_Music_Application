package com.example.challenge.User_Authentication.services;

import com.example.challenge.User_Authentication.domain.User;
import com.example.challenge.User_Authentication.exception.UserNotFoundException;


import java.util.List;

public interface UserService {

    public User addUser(User user);
    public List<User> getAllUser();
    public boolean deleteUser(int userId) throws UserNotFoundException;
    public User findByUserIdAndPassword(int userId, String password) throws UserNotFoundException;
}
