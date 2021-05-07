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

    @Query(value="SELECT * FROM room where availability = 1 and hotel_id = ? order by price desc", nativeQuery=true)
    List<Room> searchRoomPriceDESC (Long hotelId);

    @Query(value = "SELECT * FROM heroku_1ec10e1299b4c6a.room where availability = 1 and hotel_id = ?1 and capacity = ?2", nativeQuery = true)
    List<Room> searchRoomByCapacity (Long hotelId, int capacity);

    @Query(value="select * from room where hotel_id= ?", nativeQuery=true)
    List<Room> findAllRoomByHotelId(Long id);

}
