package com.consoletest.console.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.consoletest.console.dto.ClientResponse;
import com.consoletest.console.dto.ConsoleResponse;
import com.consoletest.console.dto.FactureResponse;
import com.consoletest.console.dto.ImagesResponse;
import com.consoletest.console.dto.LocationResponse;
import com.consoletest.console.entitys.Client;
import com.consoletest.console.entitys.Console;
import com.consoletest.console.entitys.Facture;
import com.consoletest.console.entitys.Images;
import com.consoletest.console.entitys.Location;
@Service
public class MapperService {
    public ImagesResponse mapperImageToImageResponse(Images images){
        ImagesResponse imagesResponse = new ImagesResponse(
        images.getId(),
        images.getImageName(),
        images.getCreatedAt(), 
        images.getUpdatedAt())
        ;
        return imagesResponse;
    }
    public ConsoleResponse mapperConsoleToConsoleResponse(Console console){
        ConsoleResponse consoleResponse = new ConsoleResponse(
        console.getIdConsole(),
        console.getNameConsole(),
        console.getEtatConsole(),
        console.getPrixDeLocationParJour(),
        console.getModel(),
        console.getNumeroSerie(),
        console.getCreatedAt(),
        console.getUpdatedAt(),
        imageOrNull(console.getImages())
        );
        return consoleResponse;
    }

    

    public LocationResponse mapperLocationToLocationResponse(Location location){
        LocationResponse locationResponse = new LocationResponse(
            location.getId(), 
            location.getDatDebutLocation(), 
            location.getDateFinLocation(), 
            location.getMontantTotalLocation(), 
            mapperConsoleToConsoleResponse(location.getConsole()),  
            location.getCreatedAt(), 
            location.getUpdatedAt()
            );
        return locationResponse;
    }
    public ClientResponse mapperClientToClientResponse(Client client){
        ClientResponse clientResponse = new ClientResponse(
            client.getIdClient(), 
            client.getNom(), 
            client.getAdresse(), 
            client.getEmail(), 
            client.getNumTelephone(), 
            client.getMotDePasse(), 
            locationOrNull(client.getLocation()),
            client.getCreatedAt(), 
            client.getUpdatedAt()
            );
        return clientResponse;
    }
    public FactureResponse mapperFactureToFactureResponse(Facture facture){
        FactureResponse factureResponse = new FactureResponse(
        facture.getIdFacture(), 
        facture.getNumeroFacture(), 
        facture.getDateFacture(), 
        facture.getIsPaye(), 
        mapperLocationToLocationResponse(facture.getLocation()),
        mapperClientToClientResponse(facture.getClient()),
        facture.getCreatedAt(), 
        facture.getUpdatedAt());
        return null;
    }

    public List<ImagesResponse> imageOrNull(List<Images> images){
        if(images == null){
            return null;
        }
        return images.stream().map(image-> mapperImageToImageResponse(image))
        .collect(Collectors.toList());
        
    }

    public List<FactureResponse> factureOrNull(List<Facture> factures){
        if(factures==null){
            return null;
        }
        return factures.stream().map(facture -> mapperFactureToFactureResponse(facture))
        .collect(Collectors.toList());
    }

    public List<LocationResponse> locationOrNull(List<Location> locations){
        if (locations==null) {
            return null;
        }
        return locations.stream().map(location ->
             mapperLocationToLocationResponse(location))
             .collect(Collectors.toList());
    }
}
