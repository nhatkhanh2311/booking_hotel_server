package com.example.demo.service;

import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    public void saveRoom(Room room){roomRepository.save(room);}
    public Room getRoomById(Long id) {
        return roomRepository.getOne(id);
    }

    public Room updateRoom(Long id) {
        Room room = roomRepository.getOne(id);
        return room;
    }

    public List<Room> getAllRoomByHotelId(Long id) {
       return roomRepository.findAllRoomByHotelId(id);
    }


    public List<Room> searchRoomByPriceDESC(Long hotelId){
        return  roomRepository.searchRoomPriceDESC(hotelId);
    }
    public List<Room> searchRoomByCapacity(Long hotelId, int capacity){
        return  roomRepository.searchRoomByCapacity(hotelId,capacity);
    }

}
