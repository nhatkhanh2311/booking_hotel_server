package com.example.demo.service;

import com.example.demo.entity.Hotel;
import com.example.demo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    /* find all hotel by director Id.
    * */
    public List<Hotel> findAllHotelByHotelOwnerId (long id){
        return hotelRepository.findAllByHOwnerId(id);
    }
    /* find hotel by hotel id
    * */
    public  Hotel  findHotelById (long id){
        return  hotelRepository.findById(id);
    }

    public void saveHotel(Hotel hotel){hotelRepository.save(hotel);}

    public List<Hotel> getAllHotelsByCityName(String cityName) {
        return hotelRepository.findAllByCityName(cityName);
    }
    public List<Hotel> findRandomHotel(){return  hotelRepository.findRandomHotel();}
    public void deleteHotel(Long id) {
        hotelRepository.deleteHotel(id);
    }

}
