package com.consoletest.console.dto;

import java.time.LocalDateTime;
import java.util.Date;


public record ImagesResponse(
    String id,
    String image,
    Date createdAt,
    Date updatedAt
) {
    
}
