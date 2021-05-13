package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.payload.reponse.Message;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.payload.request.UpdateInformationRequest;
import com.example.demo.repository.ConfirmationTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ResponeAPICotroller {

    @Autowired
    HotelService hotelService;

    @Autowired
    RoomService roomService;

    @Autowired
    DateService dateService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    GetUserFromToken getUserFromToken;

    @GetMapping("/message")
    public ResponseEntity<?> message() {
        List<Message> messageResponses = new ArrayList<>();

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
    }

    @GetMapping(value = "/forgot-password/{email}")
    public ResponseEntity<?> forgotUserPassword(@PathVariable("email") String email) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            // Create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
            // Save it
            confirmationTokenRepository.save(confirmationToken);
            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "https://booking-hotel-server.herokuapp.com/confirm-reset/"+confirmationToken.getConfirmationToken());

            // Send the email
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok().body(new MessageResponse("successForgotPassword"));
        } else {
            return ResponseEntity.ok().body("email does not exist");
        }
//        return modelAndView;
    }

    @PostMapping(value = "/confirm-reset/{token}")
    public ResponseEntity<?> confirmPassword(@PathVariable("token") String token, @RequestParam("password") String passwordRequest) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        User user = confirmationToken.getUser();
        user.setPassword(encoder.encode(passwordRequest));

        userRepository.save(user);

        return ResponseEntity.ok().body(new MessageResponse("change password successfully"));
    }

    @PostMapping(value = "/update-information")
    public ResponseEntity<?> updateInformation(@RequestHeader("Authorization") String token) {
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/update-information/save")
    public ResponseEntity<?> updateInformation(@RequestHeader("Authorization") String token, @RequestBody UpdateInformationRequest userRequest) {
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
        user.setPassword(encoder.encode(userRequest.getPassword()));
        UserDetail userDetail = user.getUserDetails();
        userDetail.setBirth(userRequest.getBirth());
        userDetail.setNameUserDetail(userRequest.getNameUserDetail());
        userDetail.setPhoneNumber(userRequest.getPhoneNumber());
        user.setUserDetail(userDetail);

        userRepository.save(user);
        return ResponseEntity.ok().body("Update successfully");
    }
}
