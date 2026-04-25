package com.saveImage.imageFiles.repository;

import com.saveImage.imageFiles.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
