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

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping(value = "/hotel")
    public ResponseEntity<?> getAllHotel(@RequestHeader("Authorization") String token) {
        String newToken = token.substring(7);
        User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);
        List<Hotel> hotels = hotelService.findAllHotelByHotelOwnerId(hOwner.getId());

        return ResponseEntity.ok().body(hotels);
    }

    @PostMapping(value = "/hotel/new-hotel", consumes = {"multipart/form-data"})
    public ResponseEntity<?> addHotell(@RequestParam("hotelRequest") String jsonHotel, @RequestParam(required = false, name = "images") MultipartFile[] images, @RequestHeader("Authorization") String token){

        try {
            String newToken = token.substring(7);
            User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);
            if(images == null){
                return ResponseEntity.ok(new MessageResponse("image is empty"));
            } else {
                List<Image> imageList = imageService.addListImage(images);
                Gson gson = new Gson();
                HotelRequest hotelRequest = gson.fromJson(jsonHotel, HotelRequest.class) ;

                Hotel hotel = new Hotel();
                hotel.sethOwner(hOwner);
                hotel.setImages(imageList);
                hotel.setName(hotelRequest.getName());
<<<<<<< HEAD
//                hotel.setStandard(hotelRequest.getStandard());
=======
                hotel.setStandard(hotelRequest.getStandard());
>>>>>>> master

                Localization localization = new Localization();
                localization.setCity(hotelRequest.getLocalization().getCity());
                localization.setCountry(hotelRequest.getLocalization().getCountry());
                localization.setStreet(hotelRequest.getLocalization().getStreet());
                hotel.setAddress(localization);

                localizationService.saveLoacation(localization);
                hotelService.saveHotel(hotel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ResponseEntity.ok(new MessageResponse("add hotel successfully"));
    }

<<<<<<< HEAD
    @GetMapping(value = "/hotel")
    public ResponseEntity<?> getAllHotel(@RequestHeader("Authorization") String token) {
        String newToken = token.substring(7);
        User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);
        List<Hotel> hotels = hotelService.findAllHotelByHotelOwnerId(hOwner.getId());

        return ResponseEntity.ok().body(hotels);
    }
//    @GetMapping(value = "/hotel/{hotelId}")
//    public ResponseEntity<?> getAllRoomByHotelId(@PathVariable("hotelId") Long hotelId){
//        List<Room> roomOfHotel = roomService.getAllRoomByHotelId(hotelId);
//        return ResponseEntity.ok().body(roomOfHotel);
//    }
=======
    @GetMapping(value = "/hotel/{hotelId}")
    public ResponseEntity<?> getAllRoom(@PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.ok().body(roomService.getAllRoomByHotelId(hotelId));
    }
>>>>>>> master

    @PostMapping("/hotel/{hotelId}/new-room")
    public ResponseEntity<?> addRoom(@PathVariable("hotelId") Long hotelId, @RequestParam(name = "images", required = false) MultipartFile[] images, @RequestParam("roomRequest") String jsonRoom){
        try {
            if(images == null){
                ResponseEntity.ok(new MessageResponse("image is empty"));
            } else {
                List<Image> imageRoomList = imageService.addListImage(images);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new MessageResponse("add room successfully"));
    }




}
