package com.example.demo.service;

import com.example.demo.entity.BookingRoom;
<<<<<<< HEAD
import com.example.demo.entity.User;
=======
>>>>>>> origin/master
import com.example.demo.repository.DateRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.*;
=======
//import org.joda.time.LocalDate;
=======
>>>>>>> origin/master

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
>>>>>>> origin/master

@Service
public class DateService  {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DateRepository dateRepository;

    /**
     * Gives start date by String from thymeleaf form
     *
     * @param from
     *            - String to be parsed to LocalDate
     * @return LocalDate of start date.
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public LocalDate startDate(String from) {
        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(from, formatter);

=======
    public LocalDate startDate(String from) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(from, dtf);
>>>>>>> origin/master
        return localDate;
    }

    /**
     * Gives end date by String from thymeleaf form
     *
     * @param to - String to be parsed to LocalDate
     * @return LocalDate of end date.
     */
    public LocalDate endDate(String to) {
<<<<<<< HEAD
        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(to, formatter);

        return localDate;
    }
    /**
     * Check whether dates given as a String are correct. End date must be after the
     * start date and start date must not be before today.
     *
     * @param from
     *            - String of start date.
     * @param to
     *            - String of end date.
     * @return true or false depending on compared dates.
     */
    public boolean checkDates(String from, String to) {
        if (startDate(from).isAfter(endDate(to)) || startDate(from).isBefore(LocalDate.now())) {
            return false;
        } else {
            return true;
        }
    }
    /*
       Find all dates by given room id
     */
    public BookingRoom[] findByRoomId(long id){
        return dateRepository.findAllByRoomId(id);
    }

    public void bookRoom(String from, String to, long id, User user) {
        BookingRoom bookingRoom = new BookingRoom();
        bookingRoom.setStart(startDate(from));
        bookingRoom.setEnd(endDate(to));
        bookingRoom.setRoom(roomRepository.findById(id));
        bookingRoom.setHost(user);
        dateRepository.save(bookingRoom);
    }

    /** Gives Date for room where host is given by id/
     * @param room - id of the room.
     * @param host - id of user who is a host in the room.
     * @return
     */
    public BookingRoom findyByRoomAndHost(long room, long host) {
        return dateRepository.findByRoomIdAndHostId(room, host);
=======
//    public LocalDate startDate(String from) {
//        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
//        return dtf.parseLocalDate(from);
//    }
//
//    /**
//     * Gives end date by String from thymeleaf form
//     *
//     * @param to - String to be parsed to LocalDate
//     * @return LocalDate of end date.
//     */
//    public LocalDate endDate(String to) {
//        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
//        return dtf.parseLocalDate(to);
//    }
=======
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(to, dtf);
        return localDate;
    }
>>>>>>> origin/master
//    /**
//     * Check whether dates given as a String are correct. End date must be after the
//     * start date and start date must not be before today.
//     *
//     * @param from
//     *            - String of start date.
//     * @param to
//     *            - String of end date.
//     * @return true or false depending on compared dates.
//     */
//    public boolean checkDates(String from, String to) {
//        if (startDate(from).isAfter(endDate(to)) || startDate(from).isBefore(LocalDate.now())) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//    /*
//       Find all dates by given room id
//     */
//    public Date[] findByRoomId(long id){
//        return dateRepository.findAllByRoomId(id);
//    }
//
//    public void bookRoom(String from, String to, long id, User user) {
//        Date date = new Date();
//        date.setStart(startDate(from));
//        date.setEnd(endDate(to));
//        date.setRoom(roomRepository.findById(id));
//        date.setHost(user);
//        dateRepository.save(date);
//    }
//
//    /** Gives Date for room where host is given by id/
//     * @param room - id of the room.
//     * @param host - id of user who is a host in the room.
//     * @return
//     */
//    public Date findyByRoomAndHost(long room, long host) {
//        return dateRepository.findByRoomIdAndHostId(room, host);
//    }


    public List<BookingRoom> getAllRoomByDateBooking(Date dateStart, Date dateEnd ) {
        return dateRepository.findRoomByDateBooking(dateStart, dateEnd, dateStart, dateEnd);
>>>>>>> origin/master
    }
}
