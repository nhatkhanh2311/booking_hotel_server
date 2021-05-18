package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.payload.reponse.Anh_MatHangNotFoundException;
import com.example.demo.payload.reponse.Message;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.SearchRequest;
import com.example.demo.payload.request.UpdateInformationRequest;
import com.example.demo.repository.ConfirmationTokenRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
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

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

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
        messageResponses.add(new Message("user bookingroom thành công", "Done booking"));

        return ResponseEntity.ok().body(messageResponses);
    }

    @GetMapping("/apiList")
    public ResponseEntity<?> apiList() {

        List<Message> apiList = new ArrayList<>();
        apiList.add(new Message("signin", "https://hotels-booking-server.herokuapp.com/signin"));
        apiList.add(new Message("signup", "https://hotels-booking-server.herokuapp.com/signup"));
        apiList.add(new Message("director - list hotels", "https://hotels-booking-server.herokuapp.com/director/hotel"));
        apiList.add(new Message("director - new hotel", "https://hotels-booking-server.herokuapp.com/director/hotel/new-hotel"));
        apiList.add(new Message("director - list room", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}"));
        apiList.add(new Message("director - new room", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/new-room"));
        apiList.add(new Message("director - get hotel update", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/update"));
        apiList.add(new Message("director - save hotel update", "https://hotels-booking-server.herokuapp.com/director/hotel/{hotelId}/new-room"));
        apiList.add(new Message("search", "https://hotels-booking-server.herokuapp.com/search2"));
        apiList.add(new Message("user - booking", "https://hotels-booking-server.herokuapp.com/user/book/{idRoom}/{from}/{to}"));
        apiList.add(new Message("user - cancel booking", "https://hotels-booking-server.herokuapp.com/user/cancelBooing/{bookingId}"));
        apiList.add(new Message("get information to update", "https://hotels-booking-server.herokuapp.com/update-information"));
        apiList.add(new Message("save update-information", "https://hotels-booking-server.herokuapp.com/update-information/save"));
        apiList.add(new Message("forgot password", "https://hotels-booking-server.herokuapp.com/forgot-password/{email}"));
        apiList.add(new Message("reset password", "https://hotels-booking-server.herokuapp.com/confirm-reset/{token}"));
        apiList.add(new Message("admin - thống kê số lượng hotel ở mỗi thành phố", "https://hotels-booking-server.herokuapp.com/admin/thongke"));
        apiList.add(new Message("admin - mở khoá tài khoản director", "https://hotels-booking-server.herokuapp.com/admin/getDirector/unlock/{directorId}"));

        return ResponseEntity.ok().body( apiList);
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

        return ResponseEntity.ok(roomList);
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
                    + "https://hotels-booking-server.herokuapp.com/confirm-reset/"+confirmationToken.getConfirmationToken());

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

    @GetMapping(value = "/update-information")
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

    @PostMapping(value = "/saveImg")
    public ResponseEntity<?> upload(@RequestParam("img") MultipartFile multipartFile ) {
        try {
            imageService.save(new Image(multipartFile.getBytes()));
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/mat-hang/anh-mat-hang/{maAnh}")
    public ResponseEntity<?> getAnh(@PathVariable("maAnh") long maAnh) throws Anh_MatHangNotFoundException {
        Image anh_MatHang = null;
//        try {
            anh_MatHang = imageService.findOne(maAnh);
//			jsonObject.put("", value)
//        } catch (Anh_MatHangNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anh khong ton tai trong CSDL", e);
//        }
        return ResponseEntity.ok(anh_MatHang.getImg());
    }

    @GetMapping(value = "/img/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(imageRepository.getOne(id).getImg());
    }
}
