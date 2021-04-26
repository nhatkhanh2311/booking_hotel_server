package com.example.demo.service;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Localization;
import com.example.demo.entity.Room;
import com.example.demo.repository.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocalizationService {

    @Autowired
    LocalizationRepository localizationRepository;
    public Localization newLocalization(Localization localization) {
        Localization tmp = localization;
        localizationRepository.save(tmp);
        return tmp;
    }

    /** Method to get all cities where rooms are located.
     * @return List of cities.
     */
    public List<String> findAllCities(){
        return localizationRepository.findAllCities();
    }

    /** Save given hotel to given localization.
     * @param localization - localization of the hotel.
     * @param hotel - hotel of given localization.
     */
    public void hotelLocalization(Localization localization, Hotel hotel) {
        localization.setHotel(hotel);
        localizationRepository.save(localization);
    }

    /** Save given room to given localization.
     * @param localization - localization of the room.
     * @param room - room of given localization.
     */
    public void roomLocalization(Localization localization, Room room) {
        localization.setRoom(room);
        localizationRepository.save(localization);
    }
}
