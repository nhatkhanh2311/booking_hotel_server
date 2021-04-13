package com.example.demo;

import com.example.demo.models.ERole;
import com.example.demo.models.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
//        Role role = new Role();
//        role.setName(ERole.ROLE_ADMIN);
//        role.setName(ERole.ROLE_USER);
//        System.out.println(role.getName() + "casi ni de cho de nhin duco chwua");
    }

}
