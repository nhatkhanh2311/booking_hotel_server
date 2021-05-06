package com.example.demo.controller;

import com.example.demo.payload.reponse.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResponeAPICotroller {
    @PostMapping("/message")
    public ResponseEntity<?> message() {
        List<Message> messageResponses = new ArrayList<>();
        messageResponses.add(new Message("signup trùng username", "Error: Username is already taken!"));
        messageResponses.add(new Message("sigup trùng email", "Error: Username is already taken!"));
        messageResponses.add(new Message("signup thành công", "User registered successfully!"));

        return ResponseEntity.ok(messageResponses);
    }
}
