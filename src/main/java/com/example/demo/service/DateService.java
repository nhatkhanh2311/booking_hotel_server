package com.example.demo.service;

import com.example.demo.models.Date;
import com.example.demo.models.User;
import com.example.demo.repository.DateRepository;
import com.example.demo.repository.RoomRepository;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.joda.time.LocalDate;

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
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dtf.parseLocalDate(from);
    }

    /**
     * Gives end date by String from thymeleaf form
     *
     * @param to - String to be parsed to LocalDate
     * @return LocalDate of end date.
     */
    public LocalDate endDate(String to) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dtf.parseLocalDate(to);
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
    public Date[] findByRoomId(long id){
        return dateRepository.findAllByRoomId(id);
    }

    public void bookRoom(String from, String to, long id, User user) {
        Date date = new Date();
        date.setStart(startDate(from));
        date.setEnd(endDate(to));
        date.setRoom(roomRepository.findById(id));
        date.setHost(user);
        dateRepository.save(date);
    }

    /** Gives Date for room where host is given by id/
     * @param room - id of the room.
     * @param host - id of user who is a host in the room.
     * @return
     */
    public Date findyByRoomAndHost(long room, long host) {
        return dateRepository.findByRoomIdAndHostId(room, host);
    }

}
