package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room>	findAllByHotelId(long id);
    List<Room> findAllByHotelAddressCity(String city);

    /** Gives list of rooms for which user by given id is a host.
     * @param id - id of user who is host in rooms returned in the list.
     * @return
     */
    @Query(value="select * from room where host.id= ?", nativeQuery=true)
    List<Room> findAllHost(long id);
    Room findById(long id);

}
