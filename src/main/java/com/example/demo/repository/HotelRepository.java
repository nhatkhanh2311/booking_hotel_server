package com.example.demo.repository;

import com.example.demo.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel,Long > {
    @Query(value="select * from hotel where h_owner_id =?", nativeQuery=true)
    List<Hotel> findAllByHOwnerId (long id);
    Hotel findById (long id);


    @Query(value = "select * from hotel where localization_id =?", nativeQuery=true)
            List<Hotel> findAllByCityName(Long id);
//    @Query(value="SELECT h.id, h.name, l.city FROM hotel h join localization l on  l.id= h.localization_id where l.city = ?", nativeQuery=true)
//    List<Hotel> findAllByCityName (String cityName);
}
