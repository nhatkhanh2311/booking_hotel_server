package com.example.hotelbooking.Controller.GuestController;

import com.example.hotelbooking.Entity.Guest.Guest;
import com.example.hotelbooking.Service.GuestService.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {
    @Autowired
    private GuestService guestService;
    @PostMapping("/registerGuest")
    public Guest registerGuest(@RequestBody  Guest guest){
        return guestService.createGuest(guest);
    }
}
