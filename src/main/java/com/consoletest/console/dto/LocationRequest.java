package com.consoletest.console.dto;

import java.time.LocalDateTime;

import com.consoletest.console.entitys.Client;
import com.consoletest.console.entitys.Console;


public record LocationRequest(
    LocalDateTime datDebutLocation,
    LocalDateTime dateFinLocation,
    Console console,
    Client client
) {
    
}
