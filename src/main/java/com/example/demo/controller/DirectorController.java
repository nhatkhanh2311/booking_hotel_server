package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.HotelRequest;
import com.example.demo.payload.request.RoomRequest;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.HotelService;
import com.example.demo.service.ImageService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.RoomService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/director")
public class DirectorController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private GetUserFromToken getUserFromToken;
    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/hotel/new-hotel", consumes = {"multipart/form-data"})
    public ResponseEntity<?> addHotell(@RequestParam("hotelRequest") String jsonHotel, @RequestParam("images") MultipartFile[] images, @RequestHeader("Authorization") String token){

        try {
            String newToken = token.substring(7);
            User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);
            if(images == null){
                ResponseEntity.ok(new MessageResponse("image is empty"));
            }

            List<Image> imageList = imageService.addListImage(images);
            Gson gson = new Gson();
            HotelRequest hotelRequest = gson.fromJson(jsonHotel, HotelRequest.class) ;

            Hotel hotel = new Hotel();
            hotel.sethOwner(hOwner);
            hotel.setImages(imageList);
            hotel.setName(hotelRequest.getName());

            Localization localization = new Localization();
            localization.setCity(hotelRequest.getLocalization().getCity());
            localization.setCountry(hotelRequest.getLocalization().getCountry());
            localization.setStreet(hotelRequest.getLocalization().getStreet());
            hotel.setAddress(localization);

            localizationService.saveLoacation(localization);
            hotelService.saveHotel(hotel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  ResponseEntity.ok(new MessageResponse("add hotel successfully"));
    }

    @PostMapping("/hotel/{hotelId}/new-room")
    public ResponseEntity<?> addRoom(@PathVariable("hotelId") Long hotelId, @RequestParam("images") MultipartFile[] images, @RequestParam("roomRequest") String jsonRoom){
        try {
            List<Image> imageRoomList = imageService.addListImage(images);

            if(images == null){
                ResponseEntity.ok(new MessageResponse("image is empty"));
            }

            Hotel hotel = hotelService.findHotelById(hotelId);
            Gson gson = new Gson();
            RoomRequest roomRequest = gson.fromJson(jsonRoom, RoomRequest.class);
            Room room = new Room();
            room.setHotel(hotel);
            room.setImages(imageRoomList);
            room.setType(roomRequest.getType());
            room.setArea(roomRequest.getArea());
            room.setCapacity(roomRequest.getCapacity());
            room.setDescription(roomRequest.getDescription());
            room.setName(roomRequest.getName());
            room.setPrice(roomRequest.getPrice());
            room.setAdded(LocalDate.now());

            roomService.saveRoom(room);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(new MessageResponse("add room successfully"));
    }

    @PostMapping(value = "/hotel")
    public ResponseEntity<?> getAllHotel(@RequestHeader("Authorization") String token) {
        String newToken = token.substring(7);
        User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);

        List<Hotel> hotels = hotelService.findAllHotelByHotelOwnerId(hOwner.getId());
        return ResponseEntity.ok().body(hotels);
    }

}
