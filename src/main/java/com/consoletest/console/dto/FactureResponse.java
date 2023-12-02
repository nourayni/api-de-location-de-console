package com.consoletest.console.dto;

import java.time.LocalDateTime;
import java.util.Date;



public record FactureResponse(
    String idFacture,
    String numeroFacture,
    Date dateFacture,
    Boolean isPaye,
    LocationResponse location,
    ClientResponse clientResponse,
    Date createdAt,
    Date updatedAt
) {
    
}
