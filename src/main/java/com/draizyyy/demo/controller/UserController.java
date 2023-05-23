package com.draizyyy.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.draizyyy.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }
    @GetMapping("/{mail}")
    public ResponseEntity<User> getByMail(@PathVariable String mail) {
        for (User user1: users) {
            System.out.println("getUser: id: " + user1.id + " mail: " + user1.mail + " name: " + user1.name + " surname: " + user1.surname);
            if (user1.getMail().equals(mail)) {
                return ResponseEntity.ok(user1);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<List<User>> addUser(@RequestBody User newUser) {
        newUser.setId((long) users.size());
        System.out.println("newUser: " + newUser.id + " " + newUser.mail + " " + newUser.name + " " + newUser.surname);
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
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/someone")
    public User getSomeone() {
        return new User("bima@gmail.ru", "bima", "dur");
    }
}
