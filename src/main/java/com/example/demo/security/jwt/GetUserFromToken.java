package com.example.demo.security.jwt;

<<<<<<< HEAD
import com.example.demo.entity.User;
=======
<<<<<<< HEAD
import com.example.demo.models.User;
=======
import com.example.demo.entity.User;
>>>>>>> thai
>>>>>>> origin/master
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class GetUserFromToken {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    public User getUser(String token) {
        int id = jwtUtils.getUserIdFromJWT(token);
        return  userService.getUserById(id);
    }
}
