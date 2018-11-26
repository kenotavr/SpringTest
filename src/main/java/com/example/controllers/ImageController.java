package com.example.controllers;

import com.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Value("${files.upload.baseDir}")
    private String uploadPath;


    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() throws IOException {
        return "uploadFile";
    }


    @PostMapping(value = "/uploadFile")
    public ResponseEntity upload(@RequestParam("pic") MultipartFile pic) {
        if (!pic.isEmpty()) {
            try {
                imageService.uploadile(pic);
                return ResponseEntity.ok().body(uploadPath + "/" + imageService.getFileNameImage());
            } catch (IOException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Empty file");
        }
    }
}
