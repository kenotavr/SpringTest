package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void uploadile(MultipartFile file) throws IOException;

    String getFileNameImage();
}
