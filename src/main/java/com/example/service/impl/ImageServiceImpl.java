package com.example.service.impl;

import com.example.domain.Image;
import com.example.repository.ImageRepository;
import com.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    private Image image = new Image();

    @Value("${files.upload.baseDir}")
    private String uploadPath;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void uploadile(MultipartFile file) throws IOException {
        String uuidFile = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        image.setName(uuidFile + originalFileName);
        file.transferTo(new File(uploadPath + "/" + image.getName()));
        imageRepository.save(image);
    }

    @Override
    public String getFileNameImage() {
        return image.getName();
    }
}
