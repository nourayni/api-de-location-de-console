package com.consoletest.console.dto;

public record ClientRequest(
    String nom,
    String adresse,
    String email,
    String numTelephone,
    String motDePasse
) {
    
}
