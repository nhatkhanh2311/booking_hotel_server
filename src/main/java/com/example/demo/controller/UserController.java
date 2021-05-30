package com.example.demo.controller;

import com.example.demo.entity.BookingRoom;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import com.example.demo.payload.request.BookingRequest;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    HotelService hotelService;

    @Autowired
    RoomService roomService;

    @Autowired
    LocalizationService localizationService;

    @Autowired
    DateService dateService;

    @Autowired
    GetUserFromToken getUserFromToken;

    @Autowired
    UserService userService;

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getUser(@RequestHeader(name ="Authorization") String token) {
        String newToken = token.substring(7);
        User user = getUserFromToken.getUserByUserNameFromJwt(newToken);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/search/{cityName}")
    public ResponseEntity<?> search(@PathVariable("cityName") String cityName) {
        List<Hotel> hotels = hotelService.getAllHotelsByCityName(cityName);
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getRooms());
        }
        return ResponseEntity.ok(hotels);
    }
    /*
    * Bo loc cua user
    * */

    @GetMapping(value = "/search/{hotelId}/search-priceDESC")
    public ResponseEntity<?> searchPriceDESC(@PathVariable("hotelId") Long hotelId){
        List<Room> rooms = roomService.searchRoomByPriceDESC(hotelId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(value = "/search/{hotelId}/{capacity}")
    public ResponseEntity<?> searchCapacity(@PathVariable("hotelId") Long hotelId, @PathVariable("capacity") int capacity){
        List<Room> rooms = roomService.searchRoomByCapacity(hotelId,capacity);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping(value = "/booking")
    public ResponseEntity<?> booking(@RequestBody BookingRequest bookingRequest, @RequestHeader(required = false, name ="Authorization") String token) {
        if(token == null) {
            return ResponseEntity.ok("error");
        } else {
            String newToken = token.substring(7);
            User host = getUserFromToken.getUserByUserNameFromJwt(newToken);
            Room room = roomService.getRoomById(bookingRequest.getIdRoom());

            BookingRoom bookingRoom = new BookingRoom();
            bookingRoom.setHost(host);
            bookingRoom.setStart(bookingRequest.getStart());
            bookingRoom.setEnd(bookingRequest.getEnd());
            bookingRoom.setRoom(room);
            dateService.saveBooking(bookingRoom);
            roomService.saveRoom(room);

            return ResponseEntity.ok(host.getUsername() + "room: " + room);
        }
    }

    @PostMapping("/book/{idRoom}/{from}/{to}")
    public ResponseEntity<?> booking(@PathVariable("idRoom") long idRoom, @PathVariable("from") String from, @PathVariable("to") String to,@RequestHeader(name ="Authorization") String token) {

        Room room = roomService.findOne(idRoom);
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
            dateService.bookRoom(from, to, idRoom, user); // luu vao bang bôking room
            List<User> hosts = room.getHost();
            hosts.add(user);
            roomService.saveRoom(room);

            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete booking room ");
            mailMessage.setText("Dear Mr/Ms " + user.getUserDetail().getNameUserDetail() + ",\n" +
                    "\n" +
                    "This email is to confirm your booking on " + from +" for the room  at the "+ room.getHotel().getName() +". The check-in date shall be on " + from +" and the check-out date shall be on " + to +".\n" +
                    "\n" +
                    "Further details of your booking are listed below:\n" +
                    "\n" +
                    "Number of guests: " + room.getCapacity() + " peoples. "+
                    "\n" +
                    "Room type: " + room.getType() +
                    "\n" +
                    "Total: " + dateService.numberOfDay(to,from) * room.getPrice()+" VND" +
                    "\n" +
                    "If you have any inquiries, please do not hesitate to contact me or call the hotel directly.\n" +
                    "\n" +
                    "We are looking forward to your visit and hope that you enjoy your stay.\n" +
                    "\n" +
                    "Best Regards");

            // Send the email
            emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.ok("Done booking");
    }

    @GetMapping(value = "/history-booking-before")
    public ResponseEntity<?> historyBookingBefore(@RequestHeader("Authorization") String token) {
        List<BookingRoom> historyBooking = dateService.getAllDateBeforeNow((getUserFromToken.getUserByUserNameFromJwt(token.substring(7))).getId());
        return ResponseEntity.ok().body(historyBooking);
    }

    @GetMapping(value = "/history-booking-after")
    public ResponseEntity<?> historyBookingAfter(@RequestHeader("Authorization") String token) {
        List<BookingRoom> historyBooking = dateService.getAllDateAfterNow((getUserFromToken.getUserByUserNameFromJwt(token.substring(7))).getId());
        return ResponseEntity.ok().body(historyBooking);
    }

    @Transactional
    @DeleteMapping(value = "/cancelBooing/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable("bookingId") Long bookingId, @RequestHeader("Authorization") String token){
        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
        BookingRoom bookingRoom = dateService.findOneBooking(bookingId);
        Room room = roomService.findOne(bookingRoom.getRoom().getId());
        // Create the email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete cancel booking room ");
        mailMessage.setText(
                "Dear Mr/Ms " + user.getUserDetail().getNameUserDetail() +",\n"
                + "You complete cancel the room " + room.getName() + " at the " + room.getHotel().getName() + " from: " + bookingRoom.getStart() + " to: " + bookingRoom.getEnd() +"\n"
                + "We hope you enjoy when you use my serviece, Thank you!"

        );
        // Send the email
        emailSenderService.sendEmail(mailMessage);

        dateService.huyBooking(bookingId);

        return ResponseEntity.ok("complete Cancel");
    }

}
