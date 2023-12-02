package com.consoletest.console.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consoletest.console.dto.ConsoleResponse;
import com.consoletest.console.dto.ConsoleTdo;
import com.consoletest.console.service.ConsoleService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "console")
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsoleControler {
    @Autowired
    private ConsoleService consoleService;
    @PostMapping("/")
    public ResponseEntity<ConsoleResponse> createConsole(@RequestBody ConsoleTdo consoleRequest){
        ConsoleResponse consoleResponse = consoleService.ajouterConsole(consoleRequest);
        return new ResponseEntity<ConsoleResponse>(consoleResponse,HttpStatus.OK);
    }
}
