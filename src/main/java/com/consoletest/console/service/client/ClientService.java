package com.consoletest.console.service.client;

import java.util.List;

import com.consoletest.console.dto.ClientRequest;
import com.consoletest.console.dto.ClientResponse;

public interface ClientService {
    ClientResponse createClient(ClientRequest clientRequest);
    ClientResponse getClientById(String id);
    ClientResponse updateClient(String id ,ClientRequest clientRequest);
    List<ClientResponse> listAllClient();
    void deleteClient(String idClient);
    List<ClientResponse> searchClientByNom(String nom);
}
