package com.consoletest.console.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.consoletest.console.entitys.Images;
import com.consoletest.console.enumeration.EtatConsole;


public record ConsoleResponse(
    String idConsole,
    String nameConsole,
    EtatConsole etatConsole,
    Long prixDeLocationParJour,
    String model,
    String numeroSerie,
    Date createdAt,
    Date updatedAt,
    List<ImagesResponse> images
) {
    
}
