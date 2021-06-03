package com.example.demo.repository;

import com.example.demo.entity.CancelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancelBookingRepository extends JpaRepository<CancelBooking, Long> {

    @Query(value = "select * from cancel_booking where host_id= ?", nativeQuery = true)
    List<CancelBooking> findAllByHost_Id(Long id);
}
