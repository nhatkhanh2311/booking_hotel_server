package com.example.demo.repository;

import com.example.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByHotel_Id(Long hotelId);
    List<Image> findAllByRoom_Id(Long roomId);

    @Modifying
    @Query(value ="delete from hotel_images where hotel_id = ?", nativeQuery=true)
    void deleteHotelInImg(Long id);

    @Modifying
    @Query(value ="delete from image where room_id = ?", nativeQuery=true)
    void deleteRoomInImg(Long roomId);
}
