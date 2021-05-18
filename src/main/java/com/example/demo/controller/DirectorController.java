package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.reponse.ThongKeDirector;
import com.example.demo.payload.request.HotelRequest;
import com.example.demo.payload.request.RoomRequest;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.*;
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
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;

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
                hotel.setStandard(hotelRequest.getStandard());

                Localization localization = new Localization();
                localization.setCity(hotelRequest.getLocalization().getCity());
                localization.setCountry(hotelRequest.getLocalization().getCountry());
                localization.setStreet(hotelRequest.getLocalization().getStreet());
                localization.setHotel(hotel);
                hotel.setAddress(localization);

                localizationService.saveLoacation(localization);
                hotelService.saveHotel(hotel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ResponseEntity.ok(new MessageResponse("add hotel successfully"));
    }

    @GetMapping(value = "/hotel/{hotelId}")
    public ResponseEntity<?> getAllRoom(@PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.ok().body(roomService.getAllRoomByHotelId(hotelId));
    }

    @GetMapping(value = "/hotel/{hotelId}/update")
    public ResponseEntity<?> updateHotel(@PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.ok().body(hotelService.findHotelById(hotelId));
    }

    @PostMapping(value = "/hotel/{hotelId}/update/save")
    public ResponseEntity<?> SaveUpdateHotel(@RequestParam("hotelRequest") String jsonHotel, @PathVariable("hotelId") Long hotelId,@RequestParam(required = false, name = "images") MultipartFile[] images ) {
        Localization localization = null;
        try {
            Gson gson = new Gson();
            HotelRequest hotelRequest = gson.fromJson(jsonHotel, HotelRequest.class) ;

            Hotel hotel = hotelService.findHotelById(hotelId);
            hotel.setStandard(hotelRequest.getStandard());
            hotel.setName(hotelRequest.getName());

            localization = localizationService.getLocationById(hotel.getAddress().getId());
            localization.setCity(hotelRequest.getLocalization().getCity());
            localization.setCountry(hotelRequest.getLocalization().getCountry());
            localization.setStreet(hotelRequest.getLocalization().getStreet());
            hotel.setAddress(localization);

            localizationService.saveLoacation(localization);

            hotel.setAddress(localization);
            if(images != null) {
                List<Image> imageList = imageService.addListImage(images);
                hotel.setImages(imageList);
            }
            hotelService.saveHotel(hotel);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("Save changes"));
    }



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

    @GetMapping(value = "/hotel/{hotelId}/{roomId}/update")
    public ResponseEntity<?> updateRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId) {
        return ResponseEntity.ok().body(roomService.findOne(roomId));
    }

    @PostMapping(value = "/hotel/{hotelId}/{roomId}/update/save")
    public ResponseEntity<?> SaveUpdateRoom(@RequestParam("roomRequest") String jsonRoom, @PathVariable("hotelId") Long hotelId,@PathVariable("roomId") Long roomId, @RequestParam(required = false, name = "images") MultipartFile[] images ) {
        try {
            Gson gson = new Gson();
            RoomRequest roomRequest = gson.fromJson(jsonRoom, RoomRequest.class) ;

            Room room = roomService.findOne(hotelId);
            room.setName(roomRequest.getName());
            room.setType(roomRequest.getType());
            room.setPrice(roomRequest.getPrice());
            room.setDescription(roomRequest.getDescription());
            room.setCapacity(roomRequest.getCapacity());
            room.setArea(roomRequest.getArea());

            if(images != null) {
                List<Image> imageList = imageService.addListImage(images);
                room.setImages(imageList);
            }
            roomService.saveRoom(room);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("Save changes"));
    }

    @GetMapping("/hotel/thongke/{hotelId}/{month}")
    public ResponseEntity<?> thongKeDirector(@PathVariable("hotelId") Long hotelId, @PathVariable("month") int month){
        List<ThongKeDirector> thongKeDirectors = dateService.thongKeDirectors(hotelId,month);
       return  ResponseEntity.ok().body(thongKeDirectors);
    }
}
