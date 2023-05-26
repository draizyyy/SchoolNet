package com.draizyyy.demo.controller;

import com.draizyyy.demo.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/ping")
public class PingController {
    @GetMapping()
    public Message ping() {
        return new Message("pong");
    }
}