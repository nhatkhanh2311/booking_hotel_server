package com.example.demo.controller;

import com.example.demo.entity.BookingRoom;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.payload.reponse.Message;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.service.DateService;
import com.example.demo.service.HotelService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResponeAPICotroller {

    @Autowired
    HotelService hotelService;

    @Autowired
    RoomService roomService;

    @Autowired
    DateService dateService;

    @GetMapping("/message")
    public ResponseEntity<?> message() {
        List<Message> messageResponses = new ArrayList<>();
<<<<<<< HEAD
        messageResponses.add(new Message("signup trùng username", "username is taken"));
        messageResponses.add(new Message("sigup trùng email", "email is taken"));
        messageResponses.add(new Message("signup thành công", "successfull"));
        messageResponses.add(new Message("signin thất bại", "incorrect"));
        messageResponses.add(new Message("chưa chọn ảnh", "image is empty"));
        messageResponses.add(new Message("thêm mới hotel thành công", "add hotel successfully"));
        messageResponses.add(new Message("thêm mới room thành công", "add room successfully"));

        return ResponseEntity.ok().body(messageResponses);
    }



    @PostMapping(value = "/search2")
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) {

        List<Hotel> hotels = hotelService.getAllHotelsByCityName(searchRequest.getCityName());
        List<Room> roomList = new ArrayList<>();
        List<BookingRoom> bookingRoomList = dateService.getAllRoomByDateBooking(searchRequest.getStart(), searchRequest.getEnd());

        for (Hotel hotel: hotels) {
            List<Room> rooms = roomService.getAllRoomByHotelId(hotel.getId());
            for (Room room: rooms) {
                roomList.add(room);
                for (BookingRoom bk: bookingRoomList) {
                    if(bk.getRoom().getId() == room.getId()) {
                        roomList.remove(room);
                        break;
                    }
                }
            }
        }
        List<Hotel> hotelList = new ArrayList<>();
        for (Room room: roomList) {
            Hotel hotel = room.getHotel();
            hotelList.add(hotel);
            System.out.println();
        }

        for(int i = 0; i < hotelList.size(); i++) {
            for (int j = i+1; j < hotelList.size(); j++) {
                if (hotelList.get(i).getId() == hotelList.get(j).getId()) {
                    hotelList.remove(hotelList.get(j));
                }
            }
        }
        return ResponseEntity.ok(hotelList);
=======
        messageResponses.add(new Message("signup trùng username", "Error: Username is already taken!"));
        messageResponses.add(new Message("sigup trùng email", "Error: Username is already taken!"));
        messageResponses.add(new Message("signup thành công", "User registered successfully!"));
        return ResponseEntity.ok(messageResponses);
>>>>>>> 353048239e53eb6d379d45b9526152a5dbef6544
    }
}
