<<<<<<< HEAD

package com.example.demo.controller;
import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.SignupRequest;
=======
package com.example.demo.controller;

import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetail;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.MessageResponse;
>>>>>>> origin/master
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.security.core.userdetails.UserDetails;
>>>>>>> origin/master
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

<<<<<<< HEAD
        UserDetails userDetails = new UserDetails();
        userDetails.setNameUserDetails(signUpRequest.getUserDetails().getNameUserDetails());
        userDetails.setBirth(signUpRequest.getUserDetails().getBirth());
        userDetails.setPhoneNumber(signUpRequest.getUserDetails().getPhoneNumber());
        user.setUserDetails(userDetails);
=======
        UserDetail userDetail = new UserDetail();
        userDetail.setNameUserDetail(signUpRequest.getUserDetail().getNameUserDetail());
        userDetail.setBirth(signUpRequest.getUserDetail().getBirth());
        userDetail.setPhoneNumber(signUpRequest.getUserDetail().getPhoneNumber());
        user.setUserDetail(userDetail);
>>>>>>> origin/master


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
<<<<<<< HEAD
                    case "directer":
                        Role dirRole = roleRepository.findByName(ERole.ROLE_DIRECTER)
=======
                    case "director":
                        Role dirRole = roleRepository.findByName(ERole.ROLE_DIRECTOR)
>>>>>>> origin/master
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
<<<<<<< HEAD
        userDetails.setUser(user);
=======
        userDetail.setUser(user);
>>>>>>> origin/master
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}