package com.consoletest.console.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consoletest.console.dto.ConsoleResponse;
import com.consoletest.console.dto.ConsoleTdo;
import com.consoletest.console.entitys.Console;
import com.consoletest.console.entitys.Images;
import com.consoletest.console.enumeration.EtatConsole;
import com.consoletest.console.exception.ExistingConsoleException;
import com.consoletest.console.repository.ConsoleRepository;
import com.consoletest.console.service.mapper.MapperService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ConsoleServiceImpl implements ConsoleService{

    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    private MapperService mapperService;


    @Override
    public ConsoleResponse ajouterConsole(ConsoleTdo consoleTdo) {
        
        // TODO Auto-generated method stub
        Console console = new Console().builder()
        .idConsole(UUID.randomUUID().toString())
        .nameConsole(consoleTdo.nameConsole())
        .prixDeLocationParJour(consoleTdo.prixDeLocationParJour())
        .model(consoleTdo.model())
        .numeroSerie(consoleTdo.numeroSerie())
        .etatConsole(EtatConsole.DISPONIBLE)
        .createdAt(new Date())
        .updatedAt(new Date())
        .build();
        
        Optional<Console> existedConsole = consoleRepository.findByNumeroSerie(console.getNumeroSerie());
        if(existedConsole.isPresent()){
           throw new ExistingConsoleException("ce console existe deja dans la base de donnee");
        }
        consoleRepository.save(console);
         return mapperService.mapperConsoleToConsoleResponse(console) ;
    }


    @Override
    public void supprimerConsole(String idConsole) {
        // TODO Auto-generated method stub
        Optional<Console> console = consoleRepository.findById(idConsole);
        if(console.isPresent()){
            consoleRepository.deleteById(idConsole); 
        }
        // sinon on leve une exception
        throw new UnsupportedOperationException("Unimplemented method 'supprimerConsole'");
    }


    @Override
    public Console mettreAJourConsole(String idConsole, ConsoleTdo consoleTdo) {
        Optional<Console> console = consoleRepository.findById(idConsole);
        if(console.isPresent()){
            console.get().setNameConsole(consoleTdo.nameConsole());
            console.get().setModel(consoleTdo.model());
            console.get().setPrixDeLocationParJour(consoleTdo.prixDeLocationParJour());
            console.get().setNumeroSerie(consoleTdo.numeroSerie());
            console.get().setEtatConsole(consoleTdo.etatConsole());
            console.get().setUpdatedAt(new Date());

            return consoleRepository.save(console.get());
        }
        // sinon on genere une exception
        throw new UnsupportedOperationException("Unimplemented method 'mettreAJourConsole'");
    }

    @Override
    public ConsoleResponse findConsoleById(String idConsole) {
        // TODO Auto-generated method stub
        Optional<Console> console = consoleRepository.findById(idConsole);
        if(console.isPresent()){
            return mapperService.mapperConsoleToConsoleResponse(console.get());
        }
        throw new UnsupportedOperationException("Unimplemented method 'findConcoleById'");
    }


    @Override
    public List<ConsoleResponse> findAllConsole() {
        // TODO Auto-generated method stub
        List<Console> consoleList = consoleRepository.findAll();                                                                                                                                                                                                                                                    
        if(consoleList!=null){
            return consoleList.stream().map(console -> 
            mapperService.mapperConsoleToConsoleResponse(console))
            .collect(Collectors.toList());
        }
        throw new UnsupportedOperationException("Unimplemented method 'findAllConsole'");
    }
}
