package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class Controller {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/")
    String hello() {
        return "Hello world - DACMPM";
    }

    @PostMapping(value = "/all")
    List<User> getAllUser() {
        return userService.getAllUser();
    }

}
