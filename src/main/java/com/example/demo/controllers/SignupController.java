
package com.example.demo.controllers;
import com.example.demo.models.ERole;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.UserDetails;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class SignupController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {



        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        UserDetails userDetails = new UserDetails();
        userDetails.setNameUserDetails(signUpRequest.getUserDetails().getNameUserDetails());
        userDetails.setBirth(signUpRequest.getUserDetails().getBirth());
        userDetails.setPhoneNumber(signUpRequest.getUserDetails().getPhoneNumber());
        user.setUserDetails(userDetails);


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found 1."));

            roles.add(userRole);
        } else {

            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        System.out.println("admin");
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found.2"));
                        roles.add(adminRole);
                        break;
                    case "directer":
                        Role dirRole = roleRepository.findByName(ERole.ROLE_DIRECTER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found.3"));
                        roles.add(dirRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found.4"));
                        roles.add(userRole);

                }
            });
        }
        user.setRoles(roles);
        userDetails.setUser(user);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}