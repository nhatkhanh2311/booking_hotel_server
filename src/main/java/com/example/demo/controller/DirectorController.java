package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.payload.reponse.JwtResponse;
import com.example.demo.payload.request.HotelRequest;
import com.example.demo.payload.request.RoomRequest;
import com.example.demo.repository.HotelRepository;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.*;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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

    @PostMapping("/")
    public String hello() {
//        in helloworld
        return "Hello world";
    }
    @PostMapping(value = "/hotel/new-hotel", consumes = {"multipart/form-data"})

    public ResponseEntity<String> addHotell(@RequestParam("hotelRequest") String jsonHotel, @RequestParam("images") MultipartFile[] images, @RequestHeader("Authorization") String token){

        try {
            String newToken = token.substring(7);
            User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);
            if(images == null){
                ResponseEntity.ok("Please choose the image");
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

        return  ResponseEntity.ok("Done add hotel");
    }

    @PostMapping("/hotel/{hotelId}/new-room")
    public ResponseEntity<String> addRoom(@PathVariable("hotelId") Long hotelId, @RequestParam("images") MultipartFile[] images, @RequestParam("roomRequest") String jsonRoom){
        try {
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

            roomService.saveRoom(room);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Done add room");
    }

}
