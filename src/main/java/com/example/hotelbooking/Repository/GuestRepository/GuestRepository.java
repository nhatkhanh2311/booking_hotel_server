package com.example.hotelbooking.Repository.GuestRepository;

import com.example.hotelbooking.Entity.Guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
}
