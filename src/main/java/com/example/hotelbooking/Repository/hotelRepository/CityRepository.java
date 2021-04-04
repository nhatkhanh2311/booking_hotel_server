package com.example.hotelbooking.Repository.hotelRepository;

import com.example.hotelbooking.Entity.hotel.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City,Integer> {
    public List<City> findByCountryId(int id);
    public City findByCityName (String cityName);
}
