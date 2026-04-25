package com.saveImage.imageFiles.controller;

import com.saveImage.imageFiles.entity.Image;
import com.saveImage.imageFiles.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            return imageService.saveImage(file);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            Image img = imageService.getImage(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(img.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + img.getName() + "\"")
                    .body(img.getData());

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
