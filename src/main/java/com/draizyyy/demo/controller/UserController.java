package com.draizyyy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.draizyyy.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @PostMapping
    public ResponseEntity<List<User>> addUser(@RequestBody User newUser) {
        for (User user: users) {
            if (Objects.equals(user.getId(), newUser.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        users.add(newUser);
        return ResponseEntity.ok(users);
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        int counter = 0;
        for (User user: users) {
            if (Objects.equals(user.getId(), updatedUser.getId())) {
                users.set(counter, updatedUser);
                return ResponseEntity.ok(users.get(counter));
            }
            counter++;
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/someone")
    public User getSomeone() {
        User user = new User(1L, "bima", "bima@gmail.ru", new User.Pet("Sharik", "Dog", 6));
        return user;
    }
}
