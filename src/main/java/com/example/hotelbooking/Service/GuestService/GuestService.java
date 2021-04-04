package com.example.hotelbooking.Service.GuestService;

import com.example.hotelbooking.Entity.Guest.Guest;
import com.example.hotelbooking.Repository.GuestRepository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public Guest createGuest(Guest guest){
        return guestRepository.saveAndFlush(guest);
    }


}
