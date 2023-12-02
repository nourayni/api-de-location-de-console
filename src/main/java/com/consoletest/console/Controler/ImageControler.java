package com.consoletest.console.Controler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.consoletest.console.dto.ImagesResponse;
import com.consoletest.console.service.image.ImageService;

@RestController
@RequestMapping(value = "/image")
public class ImageControler {
    @Autowired
    private ImageService imageService;
    @PostMapping("/{idConsole}")
    public ResponseEntity<ImagesResponse> createImage(@PathVariable String idConsole, 
    @RequestParam("file") MultipartFile file) {
        ImagesResponse imagesResponse = imageService.saveImage(idConsole, file);
        return new ResponseEntity<ImagesResponse>(imagesResponse,HttpStatus.CREATED);
    }
}
