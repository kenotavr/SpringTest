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
public class ImageController {

    @Value("${files.upload.baseDir}")
    private String uploadPath;


    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping(value = "/upload")
    public String upload() {
        return "uploadFile";
    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String upload(@RequestParam("img") MultipartFile img) {
        if (!img.isEmpty()) {
            try {
                imageService.uploadile(img);
                //return ResponseEntity.ok().body(uploadPath + "/" + imageService.getFileNameImage());
                return "redirect:/upload";
            } catch (IOException e) {
                //return ResponseEntity.badRequest().body(e.getMessage());
                return "redirect:/upload";

            }
        } else {
            //return ResponseEntity.badRequest().body("Empty file");
            return "redirect:/upload";

        }
    }

}
