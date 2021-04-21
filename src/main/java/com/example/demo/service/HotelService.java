package com.example.demo.service;

import com.example.demo.models.Hotel;
import com.example.demo.models.Image;
import com.example.demo.models.Localization;
import com.example.demo.models.User;
import com.example.demo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
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
    public void addHotell(Hotel hotel, Image image, Localization localization, User  user){
        hotel.sethOwner(user);
        hotel.setAddress(localization);
        hotel.setImages(image);
        hotelRepository.save(hotel);

    }
}
