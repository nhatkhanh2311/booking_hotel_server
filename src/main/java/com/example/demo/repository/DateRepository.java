package com.example.demo.repository;

import com.example.demo.entity.BookingRoom;
import com.example.demo.payload.reponse.ThongKeDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    @Query(value = "select * from booking_room where start > current_date() and room_id= ?", nativeQuery = true)
    List<BookingRoom> findAllByRoomAfterNow(long id);

    /** Gives Date for room where host is given by id/
     * @param room - id of the room.
     * @param host - id of user who is a host in the room.
     * @return
     */
    @Query(value="select * from date where room_id= ? and host_id= ?", nativeQuery=true)
    BookingRoom findByRoomIdAndHostId(long room, long host);



    @Query(value="select * from booking_room where" +
            "(DATE(end) BETWEEN ? AND ?) or " +
            "(DATE(start) between ? AND ?)", nativeQuery=true)
    List<BookingRoom> findRoomByDateBooking(LocalDate startDate, LocalDate endDate, LocalDate startDate1, LocalDate endDate1);


    @Query(value="SELECT * FROM booking_room where id not in (select id from booking_room where" +
            "(DATE(end) BETWEEN ? AND ?) or " +
            "(DATE(start) between ? AND ?))", nativeQuery=true)
    List<BookingRoom> findRoomByDateBooking(Date startDate, Date endDate, Date startDate1, Date endDate1);

    @Query(value = "SELECT DATEDIFF(?1, ?2) from booking_room", nativeQuery = true)
    int numberOfDay(String end, String start);

    @Query(value = "select * from booking_room where host_id= ?", nativeQuery = true)
    List<BookingRoom> findAllByHost_Id(Long id);


    @Query(value = "select * from booking_room where start <= current_date() and host_id= ?", nativeQuery = true)
    List<BookingRoom> findAllBookingRoomBeforeNow(Long id);

    @Query(value = "select * from booking_room where start > current_date() and host_id= ?", nativeQuery = true)
    List<BookingRoom> findAllBookingRoomAfterNow(Long id);

    @Modifying
    @Query(value = "delete from booking_room where id= ?;", nativeQuery = true)
    void huyBooking (Long boookingId);

    @Query(value = "SELECT * FROM booking_room where id = ?1", nativeQuery = true)
    BookingRoom findBookingById (Long bookingId);

    @Query(value = "SELECT  user_detail.name_user_detail, user.email, booking_room.end, booking_room.start, room.name,  datediff(end,start)*room.price as totalPrice \n" +
            " FROM room  join booking_room   on booking_room.room_id = room.id \n" +
            " join user on booking_room.host_id = user.id \n" +
            " join user_detail on user_detail.user_id = user.id    where room.hotel_id = ?1 and month(start) = ?2", nativeQuery = true)
    List<ThongKeDirector> thongKeDirector (Long hotelId, int month);

}
