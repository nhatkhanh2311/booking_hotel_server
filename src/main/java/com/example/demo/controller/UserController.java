package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Localization;
import com.example.demo.service.HotelService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    HotelService hotelService;

    @Autowired
    RoomService roomService;

    @Autowired
    LocalizationService localizationService;

    @PostMapping(value = "/search/{cityName}")
    public ResponseEntity<?> search(@PathVariable("cityName") Long city) {
//        city
//        List<Hotel> hotels = hotelService.findHotelById()
        List<Hotel> hotels = hotelService.getAllHotelsByCityName(city);
        for(Hotel hotel : hotels) {
            System.out.println(hotel.getRooms());
        }
        return ResponseEntity.ok(hotels);
    }

}
