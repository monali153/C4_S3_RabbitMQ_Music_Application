package com.example.challenge.UserTrackService.proxy;

import com.example.challenge.UserTrackService.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service", url="http://authentication-service:8082")
public interface UserProxy {

    @PostMapping("userauth/user/register")
    ResponseEntity<?> saveUser(@RequestBody User user);
}
