package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import com.example.demo.payload.reponse.RoomResponse;
import com.example.demo.payload.request.HotelRequest;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.service.HotelService;
import com.example.demo.service.RoomService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
