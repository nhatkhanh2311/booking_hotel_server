package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("")
    public ResponseEntity<List<Hotel>> randomHotel(){
        List<Hotel> hotels = hotelService.findRandomHotel();
        return ResponseEntity.ok().body(hotels);
    }

}
