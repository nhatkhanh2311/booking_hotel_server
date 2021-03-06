package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.payload.reponse.HotelSearchResponse;
import com.example.demo.payload.reponse.Message;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.PasswordRequest;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.payload.request.UpdateInformationRequest;
import com.example.demo.repository.ConfirmationTokenRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.UserDetailRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
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

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    LocalizationService localizationService;
    @Autowired
    UserDetailRepository userDetailRepository;

    @GetMapping("/message")
    public ResponseEntity<?> message() {
        List<Message> messageResponses = new ArrayList<>();

        messageResponses.add(new Message("signup tr??ng username", "username is taken"));
        messageResponses.add(new Message("sigup tr??ng email", "email is taken"));
        messageResponses.add(new Message("signup th??nh c??ng", "successfull"));
        messageResponses.add(new Message("signin th???t b???i", "incorrect"));
        messageResponses.add(new Message("th??m m???i hotel th??nh c??ng", "add hotel successfully"));
        messageResponses.add(new Message("th??m image hotel, room th??nh c??ng", "add img successfully"));

        messageResponses.add(new Message("update hotel, room th??nh c??ng", "Save changes"));
//        messageResponses.add(new Message("x??a hotel th??nh c??ng", "Delete hotel successful"));

        messageResponses.add(new Message("th??m m???i room th??nh c??ng", "add room successfully"));
        messageResponses.add(new Message("user bookingroom th??nh c??ng", "done booking"));

        messageResponses.add(new Message("C???p nh???t th??ng tin th??nh c??ng", "update successfully"));
        messageResponses.add(new Message("?????i m???t kh???u th??nh c??ng", "change password successfully"));
        messageResponses.add(new Message("Sai m???t kh???u khi ?????i m???t kh???u", "current password incorrect"));
        messageResponses.add(new Message("unlook th??nh c??ng" , "Done unlock"));


        return ResponseEntity.ok().body(messageResponses);
    }

    @GetMapping("/apiList")
    public ResponseEntity<?> apiList() {

        List<Message> apiList = new ArrayList<>();
        apiList.add(new Message("signin", "https://hotels-booking-server.herokuapp.com/signin"));
        apiList.add(new Message("signup", "https://hotels-booking-server.herokuapp.com/signup"));

        apiList.add(new Message("director - get list hotels", "https://hotels-booking-server.herokuapp.com/director/hotel"));
        apiList.add(new Message("director - new hotel", "https://hotels-booking-server.herokuapp.com/director/hotel/new-hotel"));
        apiList.add(new Message("director - new img for hotel", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/uploadImg"));
        apiList.add(new Message("director - get img of hotel", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/getImg"));

        apiList.add(new Message("director - list room", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}"));
        apiList.add(new Message("director - new room", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/new-room"));
        apiList.add(new Message("director - new img for room", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/{idRoom}/uploadImg"));
        apiList.add(new Message("director - get img of room", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/{idRoom}/getImg"));

        apiList.add(new Message("director - get hotel update", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/update"));
        apiList.add(new Message("director - save hotel update", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/update/save"));
        apiList.add(new Message("director - delete hotel", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/delete"));

        apiList.add(new Message("director - get room update", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/{idRoom}/update"));
        apiList.add(new Message("director - save room update", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/{idRoom}/update/save"));
        apiList.add(new Message("director - delete room", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/{idRoom}/delete"));

        apiList.add(new Message("admin - th???ng k?? s??? l?????ng hotel ??? m???i th??nh ph???", "https://hotels-booking-server.herokuapp.com/admin/thongke"));
        apiList.add(new Message("admin - th???ng k?? kh??ch s???n theo t??n th??nh ph???", "https://hotels-booking-server.herokuapp.com/admin/thongke/{cityName}"));
        apiList.add(new Message("admin - m??? kho?? t??i kho???n director", "https://hotels-booking-server.herokuapp.com/admin/getDirector/unlock/{directorId}"));
        apiList.add(new Message("admin - get t???t c??? c??c director ???? ????ng k?? nh??ng ch??a ??c x??c nh???n t??i kho???n", "https://hotels-booking-server.herokuapp.com/admin/getDirector"));
        apiList.add(new Message("admin - view details director ???? ????ng k?? nh??ng ch??a ??c x??c nh???n t??i kho???n", "https://hotels-booking-server.herokuapp.com/admin/getDirector/view/{directorId}"));

        apiList.add(new Message("user - booking", "https://hotels-booking-server.herokuapp.com/user/book/{idRoom}/{from}/{to}"));
        apiList.add(new Message("user - cancel booking", "https://hotels-booking-server.herokuapp.com/user/cancelBooking/{bookingId}"));
        apiList.add(new Message("user - get cancel booking", "https://hotels-booking-server.herokuapp.com/user/cancelBooking"));
        apiList.add(new Message("user - get list booking before now", "https://hotels-booking-server.herokuapp.com/user/history-booking-before"));
        apiList.add(new Message("user - get list booking after now", "https://hotels-booking-server.herokuapp.com/user/history-booking-after"));

        apiList.add(new Message("get all cities having hotel", "https://hotels-booking-server.herokuapp.com/all-cities"));
        apiList.add(new Message("search", "https://hotels-booking-server.herokuapp.com/search"));
        apiList.add(new Message("get information to update", "https://hotels-booking-server.herokuapp.com/update-information"));
        apiList.add(new Message("save update-information", "https://hotels-booking-server.herokuapp.com/update-information/save"));
        apiList.add(new Message("forgot password", "https://hotels-booking-server.herokuapp.com/forgot-password/{email}"));
        apiList.add(new Message("reset password", "https://hotels-booking-server.herokuapp.com/confirm-reset/{token}"));
        apiList.add(new Message("change password", "https://hotels-booking-server.herokuapp.com/update-information/save-password"));

        return ResponseEntity.ok().body( apiList);
    }

    List<HotelSearchResponse> hotelSearchResponseList = new ArrayList<>();

    @PostMapping(value = "/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) {

        List<Hotel> hotels = hotelService.getAllHotelsByCityName(searchRequest.getCityName());
        List<Room> roomList = new ArrayList<>();
        List<BookingRoom> bookingRoomList = dateService.getAllRoomByDateBooking(searchRequest.getStart(), searchRequest.getEnd());

        for (Hotel hotel: hotels) {
            List<Room> rooms = roomService.searchRoomByCapacity(hotel.getId(), searchRequest.getCapacity());
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
        for(Room room : roomList) {
            Hotel hotel = room.getHotel();
            hotelList.add(hotel);
        }
        for (int i = 0; i < hotelList.size(); i++ ) {
            for (int j = i+1; j < hotelList.size(); j++) {
                if (hotelList.get(i).getId() == hotelList.get(j).getId()) {
                    hotelList.remove(hotelList.get(j));
                    j--;
                }
            }
        }
        hotelSearchResponseList.clear();
        for (Hotel hotel: hotelList) {
            HotelSearchResponse hotelSearchResponse = new HotelSearchResponse();
            Hotel hotel1 = hotel;

            List<Room> rooms = new ArrayList<>();
            for(Room room: roomList) {
                if (hotel.getId() == room.getHotel().getId()) {
                    rooms.add(room);
                }
            }
            hotel1.setRooms(rooms);
            hotelSearchResponse.setHotel(hotel1);
            hotelSearchResponseList.add(hotelSearchResponse);
        }
        return ResponseEntity.ok(hotelSearchResponseList);
    }

    @PostMapping(value = "/forgot-password/{email}")
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
                    + "http://localhost:8080/reset-password/"+confirmationToken.getConfirmationToken());

            // Send the email
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok().body(confirmationToken.getConfirmationToken());
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

    @GetMapping(value = "/update-information")
    public ResponseEntity<?> updateInformation(@RequestHeader("Authorization") String token) {
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/update-information/save")
    public ResponseEntity<?> updateInformation(@RequestHeader("Authorization") String token, @RequestBody UpdateInformationRequest userRequest) {
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
//        user.setPassword(encoder.encode(userRequest.getPassword()));
        UserDetail userDetail = user.getUserDetail();
        userDetail.setPhoneNumber(userRequest.getPhoneNumber());
        userDetail.setBirth(userRequest.getBirth());
        userDetail.setNameUserDetail(userRequest.getNameUserDetail());
        user.setUserDetail(userDetail);

        userRepository.save(user);
        return ResponseEntity.ok().body("Update successfully");
//        return ResponseEntity.ok().body(user.getUserDetail());

    }

    @PostMapping(value = "/update-information/save-password")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordRequest passwordRequest) {
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
        if(encoder.matches(passwordRequest.getOldPassword(), user.getPassword() )) {
            user.setPassword(encoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(user);
            return ResponseEntity.ok().body(new MessageResponse("change password successfully"));
        } else {
            return ResponseEntity.ok().body(new MessageResponse("current password incorrect"));
        }
    }
    @PostMapping(value = "/change-avatar")
    public ResponseEntity<?> changeAvatar(@RequestHeader("Authorization") String token,@RequestParam(name = "avatar") MultipartFile avatar){
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
        UserDetail userDetail = user.getUserDetail();
        try {
            userDetail.setAvatar(avatar.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDetailRepository.save(userDetail);
        return ResponseEntity.ok().body(new MessageResponse("Change Avatar successfully"));
    }

    @GetMapping(value = "/all-cities")
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok().body(localizationService.findAllCities());
    }
}
