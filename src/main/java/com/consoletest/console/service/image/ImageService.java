package com.consoletest.console.service.image;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.consoletest.console.dto.ImagesResponse;

public interface ImageService {
    ImagesResponse saveImage(String idConsole,MultipartFile file);
    ImagesResponse findImageById(String id);
}
