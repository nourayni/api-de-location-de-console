package com.consoletest.console.entitys;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Client {
    @Id
    private String idClient;
    private String nom;
    private String adresse;
    private String email;
    private String numTelephone;
    private String motDePasse;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Location> location = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    private List<Facture> factures;
    private Date createdAt;
    
    private Date updatedAt;
}
