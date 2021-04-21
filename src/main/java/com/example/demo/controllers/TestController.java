//package com.example.demo.controllers;
//import com.example.demo.models.User;
//import com.example.demo.security.jwt.AuthEntryPointJwt;
//import com.example.demo.security.jwt.AuthTokenFilter;
//import com.example.demo.security.services.UserDetailsServiceImpl;
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//    @Autowired
//    UserService userService;
//    @PostMapping("/user/{id}")
//    public User allAccess(@PathVariable int id) {
//        return userService.getUserById(id);
//    }
//
//    @PostMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('DIRECTER') or hasRole('ADMIN')")
//    public String userAccess() {
//        return "User Content.";
//    }
//
//    @PostMapping("/directer")
//    @PreAuthorize("hasRole('DIRECTER')")
//    public String moderatorAccess() {
//        return "Moderator Board.";
//    }
//
//    @PostMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String adminAccess() {
//        return "Admin Board.";
//    }
//
//}
