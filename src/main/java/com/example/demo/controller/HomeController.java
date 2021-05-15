package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.service.HotelService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RestController
public class HomeController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public ResponseEntity<List<Hotel>> randomHotel(){
        List<Hotel> hotels = hotelService.findRandomHotel();
        return ResponseEntity.ok().body(hotels);
    }
    @PostMapping(value = "/search")
    public ResponseEntity<?> serchRoomAvaiable (@RequestBody SearchRequest searchRequest){
        List<Room> roomsAvaiableInPeriodTime = roomService.availableSearchedRooms(searchRequest.getCityName(), searchRequest.getCapacity(), searchRequest.getStart().toString(), searchRequest.getEnd().toString());
        return  ResponseEntity.ok(roomsAvaiableInPeriodTime);
    }

}
