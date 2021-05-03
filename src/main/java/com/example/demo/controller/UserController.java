package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Localization;
import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.HotelService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> search(@PathVariable("cityName") String cityName) {
        List<Hotel> hotels = hotelService.getAllHotelsByCityName(cityName);
        for(Hotel hotel : hotels) {
            System.out.println(hotel.getRooms());
        }
        return ResponseEntity.ok(hotels);
    }
    /*
    * Bo loc cua user
    * */

    @GetMapping(value = "/search/{hotelId}/search-priceDESC")
    public ResponseEntity<?> searchPriceDESC(@PathVariable("hotelId") Long hotelId){
        List<Room> rooms = roomService.searchRoomByPriceDESC(hotelId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(value = "/search/{hotelId}/{capacity}")
    public ResponseEntity<?> searchCapacity(@PathVariable("hotelId") Long hotelId, @PathVariable("capacity") int capacity){
        List<Room> rooms = roomService.searchRoomByCapacity(hotelId,capacity);
        return ResponseEntity.ok(rooms);
    }
}
