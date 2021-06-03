package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.reponse.ThongKeDirector;
import com.example.demo.payload.request.HotelRequest;
import com.example.demo.payload.request.RoomRequest;
import com.example.demo.repository.RoomRepository;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
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
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CancelBookingService cancelBookingService;

    @GetMapping(value = "/hotel")
    public ResponseEntity<?> getAllHotel(@RequestHeader("Authorization") String token) {
        String newToken = token.substring(7);
        User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);
        List<Hotel> hotels = hotelService.findAllHotelByHotelOwnerId(hOwner.getId());

        return ResponseEntity.ok().body(hotels);
    }

@PostMapping(value = "/hotel/new-hotel", consumes = {"multipart/form-data"})
public ResponseEntity<?> addHotell(@RequestParam("hotelRequest") String jsonHotel,@RequestParam(name = "images") MultipartFile[] images, @RequestHeader("Authorization") String token){

    try {
        String newToken = token.substring(7);
        User hOwner = getUserFromToken.getUserByUserNameFromJwt(newToken);
        Gson gson = new Gson();
        HotelRequest hotelRequest = gson.fromJson(jsonHotel, HotelRequest.class) ;

        Hotel hotel = new Hotel();
        for(int i = 0; i < images.length; i++) {
            imageService.save(new Image(images[i].getBytes(), hotel));
        }

        hotel.sethOwner(hOwner);

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
    } catch (IOException e) {
        e.printStackTrace();
    }
    return  ResponseEntity.ok(new MessageResponse("add hotel successfully"));
}
    @PostMapping(value = "/hotel/{hotelId}/uploadImg")
    public ResponseEntity<?> addImgHotell(@RequestParam(name = "images") MultipartFile[] images, @PathVariable("hotelId") Long hotelId){
        Hotel hotel = hotelService.findHotelById(hotelId);
        try {
            for(int i = 0; i < images.length; i++) {
                imageService.save(new Image(images[i].getBytes(), hotel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("add img successfully"));
    }

    @GetMapping(value = "/hotel/{hotelId}/getImg")
    public ResponseEntity<?> getImageHotel(@PathVariable("hotelId") Long hotelId) {
        List<Image> images = imageService.getImgByHotelId(hotelId);
        return ResponseEntity.ok().body(images);
    }

    @PostMapping(value = "/hotel/{hotelId}/{roomId}/uploadImg")
    public ResponseEntity<?> addImgRoom(@RequestParam(name = "images") MultipartFile[] images, @PathVariable("roomId") Long roomId){
        Room room = roomService.getRoomById(roomId);
        try {
            for(int i = 0; i < images.length; i++) {
                imageService.save(new Image(images[i].getBytes(), room));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("add img successfully"));
    }

    @GetMapping(value = "/hotel/{hotelId}/{roomId}/getImg")
    public ResponseEntity<?> getImageRoom(@PathVariable("roomId") Long roomId) {
        List<Image> images = imageService.getImgByRoomId(roomId);
        return ResponseEntity.ok().body(images);
    }

    @GetMapping(value = "/hotel/{hotelId}")
    public ResponseEntity<?> getAllRoom(@PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.ok().body(roomService.getAllRoomByHotelId(hotelId));
    }

    @GetMapping(value = "/hotel/{hotelId}/update")
    public ResponseEntity<?> updateHotel(@PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.ok().body(hotelService.findHotelById(hotelId));
    }

    @Transactional
    @PostMapping(value = "/hotel/{hotelId}/update/save")
    public ResponseEntity<?> SaveUpdateHotel(@RequestParam("hotelRequest") String jsonHotel, @PathVariable("hotelId") Long hotelId,@RequestParam(name = "images") MultipartFile[] images ) {
        try {
            Gson gson = new Gson();
            HotelRequest hotelRequest = gson.fromJson(jsonHotel, HotelRequest.class) ;

            Hotel hotel = hotelService.findHotelById(hotelId);
            hotel.setStandard(hotelRequest.getStandard());
            hotel.setName(hotelRequest.getName());

            Localization localization = localizationService.getLocationById(hotel.getAddress().getId());
            localization.setCity(hotelRequest.getLocalization().getCity());
            localization.setCountry(hotelRequest.getLocalization().getCountry());
            localization.setStreet(hotelRequest.getLocalization().getStreet());
            hotel.setAddress(localization);

            localizationService.saveLoacation(localization);
            hotel.setAddress(localization);
            imageService.deleteImgHotel(hotelId);
            for(int i = 0; i < images.length; i++) {
                imageService.save(new Image(images[i].getBytes(), hotel));
            }
            hotelService.saveHotel(hotel);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("Save changes"));
    }




    @PostMapping("/hotel/{hotelId}/new-room")
    public ResponseEntity<?> addRoom(@PathVariable("hotelId") Long hotelId,@RequestParam(name = "images") MultipartFile[] images, @RequestParam("roomRequest") String jsonRoom) throws IOException {
        try {
            Hotel hotel = hotelService.findHotelById(hotelId);
            Gson gson = new Gson();
            RoomRequest roomRequest = gson.fromJson(jsonRoom, RoomRequest.class);
            Room room = new Room();

            for (int i = 0; i < images.length; i++) {
                imageService.save(new Image(images[i].getBytes(), room));
            }
            room.setHotel(hotel);
            room.setType(roomRequest.getType());
            room.setArea(roomRequest.getArea());
            room.setCapacity(roomRequest.getCapacity());
            room.setDescription(roomRequest.getDescription());
            room.setName(roomRequest.getName());
            room.setPrice(roomRequest.getPrice());
            room.setAdded(LocalDate.now());
            roomService.saveRoom(room);
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("add room successfully"));
    }
//-----------------------

    @GetMapping(value = "/hotel/{roomId}/updateRoom")
    public ResponseEntity<?> update(@PathVariable("roomId") Long id) {
        return ResponseEntity.ok().body(roomRepository.getOne(id));
    }


    @GetMapping(value = "/hotel/{hotelId}/{roomId}/update")
    public ResponseEntity<?> updateRoom(@PathVariable("roomId") Long roomId, @PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.ok().body(roomService.getRoomById(roomId));
    }

//    -------------------------
    @PostMapping(value = "/hotel/{hotelId}/{roomId}/update/save")
    public ResponseEntity<?> SaveUpdateRoom(@RequestParam("roomRequest") String jsonRoom, @PathVariable("hotelId") Long hotelId,@PathVariable("roomId") Long roomId, @RequestParam(required = false, name = "images") MultipartFile[] images ) {
        try {
            Gson gson = new Gson();
            RoomRequest roomRequest = gson.fromJson(jsonRoom, RoomRequest.class) ;

            Room room = roomService.getRoomById(roomId);
            room.setName(roomRequest.getName());
            room.setType(roomRequest.getType());
            room.setPrice(roomRequest.getPrice());
            room.setDescription(roomRequest.getDescription());
            room.setCapacity(roomRequest.getCapacity());
            room.setArea(roomRequest.getArea());

            for(int i = 0; i < images.length; i++) {
                imageService.save(new Image(images[i].getBytes(), room));
            }
            roomService.saveRoom(room);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("Save changes"));
    }

    @GetMapping("/hotel/thongke/{hotelId}")
    public ResponseEntity<?> thongKeDirector(@PathVariable("hotelId") Long hotelId){
        return  ResponseEntity.ok().body(dateService.thongKeDirectors(hotelId));
    }

    @GetMapping("/hotel/thongke/{hotelId}/{month}")
    public ResponseEntity<?> thongKeDirector(@PathVariable("hotelId") Long hotelId, @PathVariable("month") int month){
        List<ThongKeDirector> thongKeDirectors = dateService.thongKeDirectors(hotelId,month);
       return  ResponseEntity.ok().body(thongKeDirectors);
    }

    @Transactional
    @PostMapping("/hotel/delete_room/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") Long roomId){
        roomService.deleteRoom(roomId);
        return  ResponseEntity.ok().body("Done delete room");
    }

    @Transactional
    @DeleteMapping(value = "/hotel/{hotelId}/delete")
    public ResponseEntity<?> deleteHotel(@PathVariable("hotelId") Long hotelId) {

        List<Room> roomList = roomService.getAllRoomByHotelId(hotelId);
        for(Room room: roomList) {
//
            roomService.deleteRoom(room.getId());
        }
        localizationService.deleteLocalizionHotel(hotelId);
        imageService.deleteImgHotel(hotelId);
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().body(new MessageResponse("Delete hotel successful"));
    }
}
