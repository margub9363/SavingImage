package com.saveImage.imageFiles.service;

import com.saveImage.imageFiles.entity.Image;
import com.saveImage.imageFiles.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    public final ImageRepository imageRepository;


    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String saveImage(MultipartFile file) throws Exception{
        Image img = new Image();
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        img.setData(file.getBytes());

        imageRepository.save(img);
        return "image saved successfully";
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
    }

}
