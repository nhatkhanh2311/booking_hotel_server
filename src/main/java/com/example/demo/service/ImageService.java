package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    private static String UPLOADED_FOLDER = "E:/DOANCNPM_2021/booking_hotel_server/src/main/resources/image";
    @Autowired
    private ImageRepository imageRepository;

    public Image addNewImage (MultipartFile file) throws IOException{

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename().replaceAll(" ", ""));
        Files.write(path, bytes);
        Image image = new Image();
        image.setPath("../image/" + file.getOriginalFilename().replaceAll(" ", ""));
        imageRepository.save(image);
        return image;
    }


    /** Sets default user's avatar while register.
     * @return
     */
    public Image defaultUserImage() {

        Image image = new Image();
        image.setPath("../image/default.jpg");
        imageRepository.save(image);
        return image;
    }
}
