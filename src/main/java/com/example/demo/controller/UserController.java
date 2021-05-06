package com.example.demo.controller;

import com.example.demo.entity.BookingRoom;
import com.example.demo.entity.Hotel;
<<<<<<< HEAD
import com.example.demo.entity.Localization;
import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
=======
import com.example.demo.entity.Room;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.service.DateService;
>>>>>>> origin/master
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

<<<<<<< HEAD
    @PostMapping(value = "/search/{cityName}")
    public ResponseEntity<?> search(@PathVariable("cityName") String cityName) {
        List<Hotel> hotels = hotelService.getAllHotelsByCityName(cityName);
        for(Hotel hotel : hotels) {
            System.out.println(hotel.getRooms());
=======
    @Autowired
    DateService dateService;

    @PostMapping(value = "/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) {

        List<Hotel> hotels = hotelService.getAllHotelsByCityName(searchRequest.getHotelName());
        List<Hotel> hotelList = new ArrayList<>();
        List<BookingRoom> bookingRoomList = dateService.getAllRoomByDateBooking(searchRequest.getStart(), searchRequest.getEnd());

        for (Hotel hotel: hotels) {
            List<Room> rooms = roomService.getAllRoomByHotelId(hotel.getId());
            Boolean isEmpty = false;
            for (Room room: rooms) {
                if(room.isAvailability() == true) {
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty)
                hotelList.add(hotel);
>>>>>>> origin/master
        }

<<<<<<< HEAD
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

    /*
    * BOOKING APIs
    * */

=======
        return ResponseEntity.ok(hotelList);
    }
>>>>>>> origin/master
}
