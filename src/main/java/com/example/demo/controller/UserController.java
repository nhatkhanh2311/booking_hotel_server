package com.example.demo.controller;

import com.example.demo.entity.BookingRoom;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import com.example.demo.payload.request.BookingRequest;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.DateService;
import com.example.demo.service.HotelService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/booking")
    public ResponseEntity<?> booking(@RequestBody BookingRequest bookingRequest, @RequestHeader("Authorization") String token) {
        if(token.equals("")) {
            return ResponseEntity.ok("error");
        } else {
            String newToken = token.substring(7);
            User host = getUserFromToken.getUserByUserNameFromJwt(newToken);
            Room room = roomService.getRoomById(bookingRequest.getIdRoom());

            BookingRoom bookingRoom = new BookingRoom();
            bookingRoom.setHost(host);
            bookingRoom.setStart(bookingRequest.getStart());
            bookingRoom.setEnd(bookingRequest.getEnd());
            bookingRoom.setRoom(room);
            dateService.saveBooking(bookingRoom);

            return ResponseEntity.ok(host.getUsername() + "room: " + room);
        }


    }
}
