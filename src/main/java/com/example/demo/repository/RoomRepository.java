package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room>	findAllByHotelId(long id);

    /** Gives list of rooms for which user by given id is a host.
     * @param id - id of user who is host in rooms returned in the list.
     * @return
     */
    @Query(value="select * from room where host.id= ?", nativeQuery=true)
    List<Room> findAllHost(long id);
    Room findById(long id);

<<<<<<< HEAD
=======

>>>>>>> 353048239e53eb6d379d45b9526152a5dbef6544
    @Query(value="SELECT * FROM room where availability = 1 and hotel_id = ? order by price desc", nativeQuery=true)
    List<Room> searchRoomPriceDESC (Long hotelId);

    @Query(value = "SELECT * FROM heroku_1ec10e1299b4c6a.room where availability = 1 and hotel_id = ?1 and capacity = ?2", nativeQuery = true)
    List<Room> searchRoomByCapacity (Long hotelId, int capacity);

<<<<<<< HEAD
    @Query(value="select * from room where hotel_id= ?", nativeQuery=true)
    List<Room> findAllRoomByHotelId(Long id);
=======

    @Query(value="select * from room where hotel_id= ?", nativeQuery=true)
    List<Room> findAllRoomByHotelId(Long id);

    @Query(value="SELECT * FROM room r join hotel h on r.hotel_id = h.id join localization l on h.localization_id = l.id where l.city = ?1 and capacity = ?2 ", nativeQuery=true)
    List<Room> findAllRoomByCapacityAnCity(String cityName, int capacity);

>>>>>>> 353048239e53eb6d379d45b9526152a5dbef6544

}
