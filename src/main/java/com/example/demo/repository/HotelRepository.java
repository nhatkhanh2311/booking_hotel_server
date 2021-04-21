package com.example.demo.repository;

import com.example.demo.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel,Long > {
    @Query(value="select * from hotel where h_owner_id =?", nativeQuery=true)
    List<Hotel> findAllByHOwnerId (long id);
    Hotel findById (long id);
}
