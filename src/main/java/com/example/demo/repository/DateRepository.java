package com.example.demo.repository;

import com.example.demo.entity.BookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DateRepository extends JpaRepository<BookingRoom, Long> {

    /** Gives an array of all start dates for room by given id.
     * @param id - id of the room for which array of start dates will be returned.
     * @return
     */
    @Query(value="select start from date where room_id=?", nativeQuery=true)
    BookingRoom[] findAllStart(long id);

    /** Gives an array of all end dates for room by given id.
     * @param id - id of the room for which array of end dates will be returned.
     * @return
     */
    @Query(value="select end from date where room_id=?", nativeQuery=true)
    BookingRoom[] findAllEnd(long id);

    /**Gives an array for all dates for room by given id.
     * @param id - id of the room for which array of dates will be returned.
     * @return
     */
    BookingRoom[] findAllByRoomId(long id);

    /** Gives Date for room where host is given by id/
     * @param room - id of the room.
     * @param host - id of user who is a host in the room.
     * @return
     */
    @Query(value="select * from date where room_id= ? and host_id= ?", nativeQuery=true)
    BookingRoom findByRoomIdAndHostId(long room, long host);
<<<<<<< HEAD
=======

    @Query(value="SELECT * FROM booking_room where id not in (select id from booking_room where" +
            "(DATE(end) BETWEEN ? AND ?) or " +
            "(DATE(start) between ? AND ?))", nativeQuery=true)
    List<BookingRoom> findRoomByDateBooking(Date startDate, Date endDate, Date startDate1, Date endDate1);

>>>>>>> origin/master

}
