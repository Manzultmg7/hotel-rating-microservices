package com.user.service.controller;


import com.user.service.entity.User;
import com.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
     public ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
     }
     @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById( @PathVariable Long userId) {
        User user= userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users= userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
