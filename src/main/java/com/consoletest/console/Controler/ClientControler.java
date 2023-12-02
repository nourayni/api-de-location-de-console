package com.consoletest.console.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consoletest.console.dto.ClientRequest;
import com.consoletest.console.dto.ClientResponse;
import com.consoletest.console.service.client.ClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/client")
@AllArgsConstructor
public class ClientControler {
    @Autowired
    private ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<ClientResponse> createClient(@RequestBody 
            ClientRequest clientRequest){
        ClientResponse clientResponse = clientService.createClient(clientRequest);
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<ClientResponse> getAllClient(){
        List<ClientResponse> clientResponses = clientService.listAllClient();
        return clientResponses;
    }
    
}
