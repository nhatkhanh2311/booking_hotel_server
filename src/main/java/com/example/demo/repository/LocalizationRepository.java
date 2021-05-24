package com.example.demo.repository;

import com.example.demo.entity.Localization;
import com.example.demo.payload.reponse.ThongKeKhachSanResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {
    /** Gives list of cities where rooms are located without repetitions.
     * @return
     */
    @Query(value="select distinct city from localization", nativeQuery=true)
    List<String> findAllCities();

    @Query (value = "SELECT city, count(city) as numberOfHotel FROM localization group by city", nativeQuery = true)
    List<ThongKeKhachSanResponse> thongKeKhachSan();

    Localization findAllByHotelId(Long id);
}
