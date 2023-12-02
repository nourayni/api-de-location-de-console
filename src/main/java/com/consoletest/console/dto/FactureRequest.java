package com.consoletest.console.dto;

import java.time.LocalDateTime;

import com.consoletest.console.entitys.Location;



public record FactureRequest(
    Long idFacture,
    String numeroFacture,
    LocalDateTime dateFacture,
    Boolean isPaye,
    Location location
) {
    
}
