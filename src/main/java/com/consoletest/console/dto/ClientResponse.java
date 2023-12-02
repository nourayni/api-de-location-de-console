package com.consoletest.console.dto;


import java.util.Date;
import java.util.List;



public record ClientResponse(
    String id,
    String nom,
    String adresse,
    String email,
    String numTelephone,
    String motDePasse,
    List<LocationResponse> locationResponses,
    Date createdAt,
    Date updatedAt
) {
    
}
