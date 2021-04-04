package com.example.hotelbooking.Service.hotelService;

import com.example.hotelbooking.Entity.hotel.City;
import com.example.hotelbooking.Repository.hotelRepository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City findAllCityByCountryId(int id){
        return cityRepository.findByCountryId(id);
    }
    public City findCityByName(String cityName){
        return  cityRepository.findByCityName(cityName);
    }
}
