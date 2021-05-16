package com.example.demo.service;

import com.example.demo.entity.BookingRoom;
import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DateService dateService;

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

    public Room findOne(Long idRoom){
        return roomRepository.getOne(idRoom);
    }


    public List<Room> searchRoomByPriceDESC(Long hotelId){
        return  roomRepository.searchRoomPriceDESC(hotelId);
    }
    public List<Room> searchRoomByCapacity(Long hotelId, int capacity){
        return  roomRepository.searchRoomByCapacity(hotelId,capacity);
    }

    public  List<Room> findAllRoomByCityAndCapacity(String cityName, int capacity){
        List<Room> roomResponses = roomRepository.findAllRoomByCapacityAnCity(cityName, capacity);
        return roomResponses;
    }

    /** Gives list of rooms that are available in exact period of time, with given capacity and located in city.
     * @param city - city of room location.
     * @param capacity - capacity of the room.
     * @param from - start date.
     * @param to - end date.
     * @return list of rooms to show on page.
     */
    public List<Room> availableSearchedRooms(String city, int capacity, String from, String to){

        List<Room> listRoomFreeInPeriodtime = new ArrayList<>();
        for (Room room : findAllRoomByCityAndCapacity(city, capacity)) {

            List<BookingRoom> bookingRooms = dateService.findAllRoomById(room.getId());
            if (bookingRooms.size() == 0) {
                listRoomFreeInPeriodtime.add(room);

            } else if (bookingRooms.size() > 0) {

                for (int i = 0; i < bookingRooms.size(); i++) {
                    if (dateService.startDate(from).isAfter(bookingRooms.get(i).getEnd()) && i == bookingRooms.size() - 1) {
                        listRoomFreeInPeriodtime.add(room);
                    } else if (dateService.startDate(from).isAfter(bookingRooms.get(i).getEnd()) && dateService.endDate(to).isBefore(bookingRooms.get(i+1).getStart())) {
                        listRoomFreeInPeriodtime.add(room);
                    }
                    else if (dateService.endDate(to).isBefore(bookingRooms.get(0).getStart())){
                        listRoomFreeInPeriodtime.add(room);
                    }
                }
            }

        }
        return listRoomFreeInPeriodtime;
    }

}
