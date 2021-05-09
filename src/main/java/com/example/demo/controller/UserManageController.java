package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.TaiKhoanNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.security.jwt.GetUserFromToken;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.UserService;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user-management")
public class UserManageController {
@Autowired
    UserService userService;
@Autowired
    JwtUtils jwtUtils;
@Autowired
    GetUserFromToken getUserFromToken;
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

//
//    /*
//    * API thay doi mat khau

//    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'USER')")
//    @PutMapping("/authorized/user/change/{userId}")
//    public ResponseEntity<Void> thayDoiMatKhau (@PathVariable Long userId, @RequestHeader("Authorization") String token, @RequestBody JSONObject jsonObject ){
//        User user = getUserFromToken.getUserByUserNameFromJwt(token.substring(7));
//        System.out.println("Day la userName" + user.getUsername());
//        boolean checkPermission = false;
//        if(userId == user.getId()){
//            checkPermission = true;
//        }
//        if(checkPermission){
//            try{
//                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//                String oldPass = jsonObject.getString("oldPass");
//                String newPass = jsonObject.getString("newPass");
//                if(!passwordEncoder.matches(oldPass, user.getPassword())){
//                    new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
//                }
//                userService.changPassword(userId, passwordEncoder.encode(newPass));
//                    return new ResponseEntity<Void>(HttpStatus.OK);
//
//                } catch (TaiKhoanNotFoundException e) {
//                e.printStackTrace();
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Khong tim thay tai khoan trong CSDL", e);
//            }
//        }
//        else {
//            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
//        }
//    }

}
