package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Image;
import com.example.demo.entity.Localization;
import com.example.demo.entity.User;
import com.example.demo.payload.reponse.JwtResponse;
import com.example.demo.payload.request.HotelRequest;
import com.example.demo.repository.HotelRepository;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.HotelService;
import com.example.demo.service.ImageService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.UserService;
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

    @PostMapping("/")
    public String hello() {
//        in helloworld
        return "Hello world";
    }
    @PostMapping(value = "/hotel/addhotel", consumes = {"multipart/form-data"})
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
        return  ResponseEntity.ok("Done");
    }

}
