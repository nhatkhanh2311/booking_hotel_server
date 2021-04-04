package com.example.hotelbooking.Controller;

import com.example.hotelbooking.Entity.hotel.City;
import com.example.hotelbooking.Service.hotelService.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeApi {
    @Autowired
    private CityService cityService;
    @GetMapping("/city/{contryId}")
    public City getCityByCountryId (@PathVariable("contryId") int countryId){
        return  cityService.findAllCityByCountryId(countryId);
    }

    @GetMapping("/{cityName}")
    public City getCityByCityName (@PathVariable("cityName") String cityName){
        return  cityService.findCityByName(cityName);
    }
}
