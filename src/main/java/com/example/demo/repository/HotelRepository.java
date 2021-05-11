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


//    @Query(value = "select * from hotel where localization_id =?", nativeQuery=true)
//            List<Hotel> findAllByCityName(Long id);

    @Query(value="SELECT * FROM hotel  join localization  on   hotel.localization_id = localization.id where localization.city = ?", nativeQuery=true)
    List<Hotel> findAllByCityName (String cityName);

    @Query(value = "SELECT * FROM hotel order by rand() limit 10;", nativeQuery = true)
    List<Hotel> findRandomHotel();

}
