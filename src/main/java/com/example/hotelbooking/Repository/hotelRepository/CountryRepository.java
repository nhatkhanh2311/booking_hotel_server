package com.example.hotelbooking.Repository.hotelRepository;

import com.example.hotelbooking.Entity.hotel.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
}

