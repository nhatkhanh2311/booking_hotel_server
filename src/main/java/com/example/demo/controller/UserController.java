package com.example.demo.controller;

import com.example.demo.entity.BookingRoom;
import com.example.demo.entity.Hotel;

import com.example.demo.entity.Room;


import com.example.demo.entity.User;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.DateService;

import com.example.demo.service.HotelService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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

    @Autowired
    DateService dateService;
    @Autowired
    GetUserFromToken getUserFromToken;

    @PostMapping(value = "/search/{cityName}")
    public ResponseEntity<?> search(@PathVariable("cityName") String cityName) {
        List<Hotel> hotels = hotelService.getAllHotelsByCityName(cityName);
        for (Hotel hotel : hotels) {
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


    @PostMapping("/book/{idRoom}/{from}/{to}")
    public ResponseEntity<?> booking(@PathVariable("idRoom") long idRoom, @PathVariable("from") String from, @PathVariable("to") String to,@RequestHeader("Authorization") String token) {

        Room room = roomService.findOne(idRoom);
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
        if (user != null) {
                dateService.bookRoom(from, to, idRoom, user); // luu vao bang bôking room
            List<User> hosts = room.getHost();
            hosts.add(user);
            roomService.saveRoom(room);
        } else {
            return ResponseEntity.ok("Please Login");
        }
        return ResponseEntity.ok("Done booking");
    }
}
