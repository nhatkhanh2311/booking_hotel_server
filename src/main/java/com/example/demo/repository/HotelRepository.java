package com.example.demo.repository;

import com.example.demo.entity.Hotel;
import com.example.demo.payload.reponse.ThongKeKhachSanAdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel,Long > {
    @Query(value="select * from hotel where h_owner_id =?", nativeQuery=true)
    List<Hotel> findAllByHOwnerId (long id);

    Hotel findById (long id);

//    @Query(value = "select * from hotel where localization_id =?", nativeQuery=true)
//            List<Hotel> findAllByCityName(Long id);

    @Query(value="SELECT * FROM hotel  join localization on hotel.id = localization.hotel_id where localization.city = ?", nativeQuery=true)
    List<Hotel> findAllByCityName (String cityName);

    @Query(value = "SELECT * FROM hotel order by rand() limit 10;", nativeQuery = true)
    List<Hotel> findRandomHotel();

    @Modifying
    @Query(value ="delete from hotel where hotel.id = ?", nativeQuery=true)
    void deleteHotel(Long id);

    @Query(value = "SELECT hotel.id as hotelId, hotel.name as hotelName, hotel.standard as hotelStandard, localization.street ,localization.city, user_detail.name_user_detail as hotelOwner, user_detail.phone_number FROM localization join hotel on localization.hotel_id = hotel.id\n" +
            "join user on hotel.h_owner_id = user.id \n" +
            "join user_detail on user.id = user_detail.user_id\n" +
            "where city like ?%" , nativeQuery = true)
    List<ThongKeKhachSanAdminResponse> thongKeAdmin(String cityName);
}
