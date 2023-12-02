package com.consoletest.console.service.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consoletest.console.dto.ClientRequest;
import com.consoletest.console.dto.ClientResponse;
import com.consoletest.console.entitys.Client;
import com.consoletest.console.exception.ClientNotFoundException;
import com.consoletest.console.exception.ExistingClientException;
import com.consoletest.console.repository.ClientRepository;
import com.consoletest.console.service.mapper.MapperService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@Service
@AllArgsConstructor
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MapperService mapperService;
    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        // TODO Auto-generated method stub
        Optional<Client> clientOptional = clientRepository
            .findByNom(clientRequest.nom());
        if(clientOptional.isPresent()){
            throw new ExistingClientException("ce client existe deja dans la base de donnee");
        }
        Client newClient = new Client().builder()
        .idClient(UUID.randomUUID().toString())
        .nom(clientRequest.nom())
        .adresse(clientRequest.adresse())
        .email(clientRequest.email())
        .numTelephone(clientRequest.numTelephone())
        .motDePasse(clientRequest.motDePasse())
        .createdAt(new Date())
        .updatedAt(new Date())
        .build();
        Client SavedClient = clientRepository.save(newClient);
        return mapperService.mapperClientToClientResponse(SavedClient);
    }

    @Override
    public ClientResponse getClientById(String id) {
        // TODO Auto-generated method stub
        Optional<Client> getClient = clientRepository.findById(id);
        if(!getClient.isPresent()){
            throw new ClientNotFoundException("client introuvable");
        }
        return mapperService.mapperClientToClientResponse(getClient.get());
   
    }

    @Override
    public ClientResponse updateClient(String id, ClientRequest clientRequest) {
        // TODO Auto-generated method stub
        Optional<Client> getClient = clientRepository.findById(id);
        if(!getClient.isPresent()){
            throw new ClientNotFoundException("client introuvable");
        }
        getClient.get().setEmail(clientRequest.email());
        getClient.get().setAdresse(clientRequest.adresse());
        getClient.get().setNom(clientRequest.nom());
        getClient.get().setNumTelephone(clientRequest.numTelephone());
        getClient.get().setMotDePasse(clientRequest.motDePasse());
        Client UpdatedClient = clientRepository.save(getClient.get());
        return mapperService.mapperClientToClientResponse(UpdatedClient);
    }

    @Override
    public List<ClientResponse> listAllClient() {
        // TODO Auto-generated method stub
        List<Client> getAllClients = clientRepository.findAll();
        return getAllClients.stream().map(client -> 
        mapperService.mapperClientToClientResponse(client)).collect(Collectors.toList());
    }

    @Override
    public void deleteClient(String idClient) {
        // TODO Auto-generated method stub
        Optional<Client> clientOpt = clientRepository.findById(idClient);
        if(clientOpt.isPresent()){
            clientRepository.delete(clientOpt.get());
        }
        throw new ClientNotFoundException(
            "impossible de supprimer ,client introuvable");
    }

    @Override
    public List<ClientResponse> searchClientByNom(String nom) {
        // TODO Auto-generated method stub
        List<Client> clientSearch = clientRepository.findByNomContaining(nom);
        return clientSearch.stream().map(client ->
        mapperService.mapperClientToClientResponse(client)).collect(Collectors.toList());
    }
    
}
