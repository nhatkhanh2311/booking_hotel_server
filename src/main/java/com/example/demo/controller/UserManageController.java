package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user-management")
public class UserManageController {
@Autowired
    UserService userService;
@Autowired
    JwtUtils jwtUtils;
/*
* API lock tai khoan
*/
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/authorized/user/lock/{userId}")
    public ResponseEntity<Void> khoaTaiKhoan(@PathVariable long userId){
        try {
            userService.lockUser(userId);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't find this account on database", e);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /*
    * API unlock tai khoan
    */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/authorized/user/unlock/{userId}")
    public ResponseEntity<Void> moKhoaTaiKhoan(@PathVariable long userId){
        try {
            userService.UnlockUser(userId);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't find this account on database", e);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
