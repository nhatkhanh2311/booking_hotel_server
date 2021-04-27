package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Image;
import com.example.demo.entity.Localization;
import com.example.demo.entity.User;
import com.example.demo.payload.reponse.JwtResponse;
import com.example.demo.repository.HotelRepository;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.HotelService;
import com.example.demo.service.ImageService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;
    @Autowired
    private HotelRepository hotelRepository;

    private JwtResponse jwtResponse;
    @Autowired
    private GetUserFromToken getUserFromToken;


    @PostMapping("/")
    public String hello() {
//        in helloworld
        return "Hello world";
    }


    @PostMapping ("hotel/addhotel")
    public ResponseEntity<String> addHotel( @RequestParam("image") MultipartFile file, @RequestHeader("Authorization") String token) {

        if (file.isEmpty()) {
            return  ResponseEntity.ok("false");
        }
        try {
            System.out.println("------------------------------------------------------");
            String newToken = token.substring(7);
            System.out.println("token la: " + newToken);
//            Localization localization = (Localization) jsonObject.get("localization");

//            List<Image> images = imageService.addListImage(files);
            Image image = imageService.addNewImage(file);
            User user = getUserFromToken.getUserByUserNameFromJwt(newToken);
            if(user == null){
                System.out.println("null user from token");
            }
            System.out.println("tai khoan co ten khach hang  la " + user.getUserDetails().getNameUserDetail());
//            Hotel hotel = new Hotel();
//            hotel.setName((String) jsonObject.get("name"));
//            hotel.setAddress(localization);
//            hotel.setImages(image);
//            hotel.sethOwner(user);
//            hotelRepository.save(hotel);

//            System.out.println(hotel.getAddress().getStreet());
//            System.out.println(hotel.gethOwner().getUsername());
//            System.out.println(hotel.getId());
//            System.out.println(hotel.getName());


        } catch (IOException e) {

        }

        return  ResponseEntity.ok("Hello");
    }

}
