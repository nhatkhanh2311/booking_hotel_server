package com.example.demo.Repository.hotelRepository;

import com.example.demo.Entity.hotel.HotelDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelDirector, Integer> {
}
