package com.consoletest.console.service.location;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consoletest.console.dto.LocationRequest;
import com.consoletest.console.dto.LocationResponse;
import com.consoletest.console.entitys.Client;
import com.consoletest.console.entitys.Console;
import com.consoletest.console.entitys.Facture;
import com.consoletest.console.entitys.Location;
import com.consoletest.console.enumeration.EtatConsole;
import com.consoletest.console.exception.ClientNotFoundException;
import com.consoletest.console.repository.ClientRepository;
import com.consoletest.console.repository.ConsoleRepository;
import com.consoletest.console.repository.FactureRepository;
import com.consoletest.console.repository.LocationRepository;
import com.consoletest.console.service.mapper.MapperService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Builder
@Transactional
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private FactureRepository factureRepository;
    


    @Override
    public LocationResponse createLocation(LocationRequest locationRequest) {
        // TODO Auto-generated method stub
        Optional <Console> console = consoleRepository.findById(locationRequest.console().getIdConsole());
        if (console==null) {
            throw new ClientNotFoundException("console introuvable");
            // lever une exception
        }
        if (!console.get().getEtatConsole().equals(EtatConsole.DISPONIBLE)) {
            //lever une exception
        }
        
        //new Location();
        Location newLocation = new Location().builder()
        .id(UUID.randomUUID().toString())
        .console(console.get())
        .client(locationRequest.client())
        .datDebutLocation(locationRequest.datDebutLocation())
        .dateFinLocation(locationRequest.dateFinLocation())
        .montantTotalLocation(prixTotalDeLocation(locationRequest,console.get()))
        .createdAt(new Date())
        .updatedAt(new Date())
        .build();


        // modifier l'etat du console loue
        Location location = locationRepository.save(newLocation);
        console.get().setEtatConsole(EtatConsole.LOUEE);
        consoleRepository.save(console.get());


        // ajouter la location au client conserne
        Optional<Client> client = clientRepository
        .findById(location.getClient().getIdClient());
        client.get().getLocation().add(location);
        clientRepository.save(client.get());

        // creation de la facture
        Optional<Client> getClient = clientRepository
        .findById(location.getClient().getIdClient());

        Facture facture = new Facture().builder()
        .idFacture(UUID.randomUUID().toString())
        .numeroFacture("console_mary"+ UUID.randomUUID().toString())
        .dateFacture(new Date())
        .isPaye(false)
        .location(location)
        .client(getClient.get())
        .createdAt(new Date())
        .updatedAt(new Date())
        .build();

        factureRepository.save(facture);



        return mapperService.mapperLocationToLocationResponse(location);
    }

    // calcule du prix total de la location
    private double prixTotalDeLocation(LocationRequest locationRequest,Console console){
        final double nbrJourDeLocation = ChronoUnit.DAYS
            .between(locationRequest.datDebutLocation(),locationRequest.dateFinLocation());
        double prixConsole = console.getPrixDeLocationParJour();
        double prixTotal = nbrJourDeLocation*prixConsole;
        return prixTotal;
    }

  


    
}
