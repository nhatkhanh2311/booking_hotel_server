package com.example.demo.service;

import com.example.demo.entity.BookingRoom;
import com.example.demo.entity.User;
import com.example.demo.repository.DateRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


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

    public LocalDate startDate(String from) {
        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(from, formatter);
        return localDate;
    }

    /**
     * Gives end date by String from thymeleaf form
     *
     * @param to - String to be parsed to LocalDate
     * @return LocalDate of end date.
     */
    public LocalDate endDate(String to) {
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
    public List<BookingRoom> findAllRoomById (Long id){
        return  dateRepository.findAllByRoomId(id);
    }

    public void bookRoom(String from, String to, long id, User user) {
        BookingRoom bookingRoom = new BookingRoom();
        bookingRoom.setStart(startDate(from));
        bookingRoom.setEnd(endDate(to));
        bookingRoom.setRoom(roomRepository.getOne(id));
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
    }

    public List<BookingRoom> getAllRoomByDateBooking(LocalDate start, LocalDate end) {
        return dateRepository.findRoomByDateBooking(start, end, start, end);
    }

    public void saveBooking(BookingRoom bookingRoom) {
        dateRepository.save(bookingRoom);
    }

    public int numberOfDay(String end, String start){
        return  dateRepository.numberOfDay(end, start);
    }

    public List<BookingRoom> getAllDateByHostId(Long hostId) {
        return dateRepository.findAllByHost_Id(hostId);
    }
}
