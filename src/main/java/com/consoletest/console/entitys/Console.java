package com.consoletest.console.entitys;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.consoletest.console.enumeration.EtatConsole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Console {
    @Id
    private String idConsole;
    private String nameConsole;
    private Long prixDeLocationParJour;
    private String model;
    private String numeroSerie;
    private EtatConsole etatConsole;
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy = "console" , cascade = CascadeType.ALL)
    private List<Location> location = new ArrayList<>();
    @OneToMany(mappedBy = "console", cascade = CascadeType.ALL)
    private List<Images> images = new ArrayList<>();
    
}
