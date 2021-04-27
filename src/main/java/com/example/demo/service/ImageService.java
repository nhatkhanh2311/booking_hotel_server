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
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private static Path currentPath = Paths.get(System.getProperty("user.dir"));
    private static Path filePath = Paths.get(currentPath.toString(), "src", "main", "resources", "image");

    private static String UPLOADED_FOLDER = filePath.toString()+ "\\";
    @Autowired
    private ImageRepository imageRepository;

    public Image addNewImage (MultipartFile file) throws IOException{

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename().replaceAll(" ", ""));
        Files.write(path, bytes);
        Image image = new Image();
        image.setPath("..\\image\\" + file.getOriginalFilename().replaceAll(" ", ""));
        imageRepository.save(image);
        return image;
    }

   public List<Image> addListImage (MultipartFile[] files) throws IOException {
      List<Image> images = new ArrayList<>();
        for(int i= 0; i< files.length; i++){
            byte[] bytes = files[i].getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + files[i].getOriginalFilename().replaceAll(" ", ""));
            Files.write(path, bytes);
            Image image = new Image();
            image.setPath("..\\image\\" + files[i].getOriginalFilename().replaceAll(" ", ""));
            imageRepository.save(image);
            images.add(image);
        }
        return images;

   }



}
