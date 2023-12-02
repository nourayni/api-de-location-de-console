package com.consoletest.console.service.image;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.consoletest.console.dto.ImageRequest;
import com.consoletest.console.dto.ImagesResponse;
import com.consoletest.console.entitys.Console;
import com.consoletest.console.entitys.Images;
import com.consoletest.console.exception.SaveImageException;
import com.consoletest.console.repository.ConsoleRepository;
import com.consoletest.console.repository.ImagesRepository;
import com.consoletest.console.service.mapper.MapperService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    private MapperService mapperService;
    @Override
    public ImagesResponse saveImage(String idConsole, MultipartFile file) {
        // TODO Auto-generated method stub
        Optional<Console> consoleOpt = consoleRepository.findById(idConsole);
        if(consoleOpt == null){
            //exception
        }
        try {
            if(!file.isEmpty() && isImageSizeValide(file) && isTypeValide(file)){
            Images images = new Images().builder()
            .id(UUID.randomUUID().toString())
            .imageName("console_"+file.getOriginalFilename()+new Date())
            .imageFile(file.getBytes())
            .console(consoleOpt.get())
            .createdAt(new Date())
            .updatedAt(new Date())
            .build();

            imagesRepository.save(images);
            consoleOpt.get().getImages().add(images);
            consoleRepository.save(consoleOpt.get());

            ImagesResponse imagesResponse = new ImagesResponse(
            images.getId(), 
            images.getImageName(), 
            images.getCreatedAt(), 
            images.getUpdatedAt());
            return imagesResponse;
        }
        } catch (Exception e) {
            new SaveImageException("imposible d'enregistre l'image "+ e.getLocalizedMessage());
        }
        return null;
       
    }
 
 
    @Override
    public ImagesResponse findImageById(String id) {
        // TODO Auto-generated method stub
        Optional<Images> imageOptional = imagesRepository.findById(id);
        if (imageOptional.isPresent()) {
            return mapperService.mapperImageToImageResponse(imageOptional.get());
        } 
        throw new UnsupportedOperationException("Unimplemented method 'findImageById'");
    }

    // verification de la taille maximal de l'image
    private Boolean isImageSizeValide(MultipartFile file){
        long maxSize = 5 * 1024 * 1024; // taille max 5MB
        return file.getSize() <= maxSize;
    }

    // verification des types d'images accepter
    // seuls les image aux format png jpg et jpeg sont accepter
    private Boolean isTypeValide(MultipartFile file){
        String contentType = file.getContentType();
        return contentType!=null &&(
            contentType.equals("image/png") || 
            contentType.equals("image/jpg") || 
            contentType.equals("image/jpeg"));
    }
   
    
}
