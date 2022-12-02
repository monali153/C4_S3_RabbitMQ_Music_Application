package com.example.challenge.User_Authentication.services;

import com.example.challenge.User_Authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{

    @Override
    public Map<Integer, String> generateToken(User user) {
        String jwtToken = null;
        jwtToken = Jwts.builder()
                .setSubject(String.valueOf(user.getUserId()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mykey").compact();

        Map<Integer, String> map = new HashMap<>();
        map.put(0,jwtToken);
        map.put(1,"User Successfully logged in");
        return map;
    }
}
