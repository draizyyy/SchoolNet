//package com.draizyyy.demo.controller;
//
//import com.draizyyy.demo.database.DayRepository;
//import com.draizyyy.demo.model.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/userEntities")
//public class DayEntityController {
//    @Autowired
//    private UserRepository userRepository;
//    @GetMapping
//    public List<UserEntity> getAll() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserEntity> getById(@PathVariable Long id) {
//        Optional<UserEntity> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        }
//        return ResponseEntity.notFound().build();
//    }
//    @PostMapping
//    public UserEntity save(@RequestBody UserEntity user) {
//        return userRepository.save(user);
//    }
//}
