package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getImgByHotelId(Long hotelId) {
        return imageRepository.findAllByHotel_Id(hotelId);
    }

    public List<Image> getImgByRoomId(Long roomId) {
        return imageRepository.findAllByRoom_Id(roomId);
    }

    public void save(Image img) {
        imageRepository.save(img);
    }

    public void deleteImgHotel(Long id) {
        imageRepository.deleteHotelInImg(id);
    }

    public void deleteImgRoom(Long id) {
        imageRepository.deleteImgRoom(id);
    }
}
