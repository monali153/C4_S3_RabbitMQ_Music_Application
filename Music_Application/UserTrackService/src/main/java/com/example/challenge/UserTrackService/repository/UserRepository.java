package com.example.challenge.UserTrackService.repository;

import com.example.challenge.UserTrackService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

    public User findByUserId(int userId);
}
