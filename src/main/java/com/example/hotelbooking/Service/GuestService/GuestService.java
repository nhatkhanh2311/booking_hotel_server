package com.example.demo.Service.GuestService;

import com.example.hotelbooking.Repository.GuestRepository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

}
