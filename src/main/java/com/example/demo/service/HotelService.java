package com.example.demo.service;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Image;
import com.example.demo.entity.Localization;
import com.example.demo.entity.User;
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
    public void addHotell(Hotel hotel, List<Image> image, Localization localization, User user){
        hotel.sethOwner(user);
        hotel.setAddress(localization);
        hotel.setImages(image);
        hotelRepository.save(hotel);
    }
    public void saveHotel(Hotel hotel){hotelRepository.save(hotel);}

    public List<Hotel> getAllHotelsByCityName(String cityName) {
        return hotelRepository.findAllByCityName(cityName);
    }
}
