package com.consoletest.console.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record LocationResponse(
    String id,
    LocalDateTime datDebutLocation,
    LocalDateTime dateFinLocation,
    double montantTotalLocation,
    ConsoleResponse consoleResponse,
    Date createdAt,   
    Date updatedAt
) {
    
}
