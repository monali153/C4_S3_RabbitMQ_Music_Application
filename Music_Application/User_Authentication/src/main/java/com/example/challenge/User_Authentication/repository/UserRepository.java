package com.example.challenge.User_Authentication.repository;


import com.example.challenge.User_Authentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserIdAndPassword(int userId, String password);
}
