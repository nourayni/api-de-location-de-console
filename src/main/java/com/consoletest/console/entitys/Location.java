package com.consoletest.console.entitys;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Location {
    @Id
    private String id;
    private LocalDateTime datDebutLocation;
    private LocalDateTime dateFinLocation;
    private double montantTotalLocation;
    @ManyToOne
    @JoinColumn(name = "console_id")
    private Console console;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
     private Facture facture;
    private Date createdAt;
    private Date updatedAt;
}
