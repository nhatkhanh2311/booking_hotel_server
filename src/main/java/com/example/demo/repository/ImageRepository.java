package com.example.demo.repository;

import com.example.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findById (long id);

    @Modifying
    @Query(value ="delete from hotel_images where hotel_id = ?", nativeQuery=true)
    void deleteHotelInImg(Long id);

    @Modifying
    @Query(value ="delete from room_images where room_id = ?", nativeQuery=true)
    void deleteRoomInImg(Long id);
}
