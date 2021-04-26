package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Image;
import com.example.demo.entity.Localization;
import com.example.demo.entity.User;
import com.example.demo.payload.reponse.JwtResponse;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.HotelService;
import com.example.demo.service.ImageService;
import com.example.demo.service.LocalizationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

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


    private JwtResponse jwtResponse;
    private GetUserFromToken getUserFromToken;

    /**
     * Adding new hotel to current user (Manager Role)
     * @param model
     * @param redirectAttributes
     * @param hotel
     *            - hotel that is being added.
     * @param localization
     *            - localization of the hotel
     * @param bresult
     * @param file
     *            - image of the hotel that is uploaded.
     * @return
     */
    @PostMapping ("hotel/addhotel")
    public String addHotel(Model model, RedirectAttributes redirectAttributes, Hotel hotel, Localization localization, BindingResult bresult,
                           @RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/manager/hotelForm";
        }

        try {
            Image image = imageService.addNewImage(file);
            Localization hotelLocalization = localizationService.newLocalization(localization);
            User user = getUserFromToken.getUser(token);

            hotelService.addHotell(hotel, image, hotelLocalization, user);
            localizationService.hotelLocalization(hotelLocalization, hotel);
            redirectAttributes.addFlashAttribute("info", "Hotel has been added.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/manager/hotels";
    }

}
