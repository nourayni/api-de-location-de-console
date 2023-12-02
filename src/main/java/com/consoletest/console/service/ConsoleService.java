package com.consoletest.console.service;


import java.util.List;

import com.consoletest.console.dto.ConsoleResponse;
import com.consoletest.console.dto.ConsoleTdo;
import com.consoletest.console.entitys.Console;

public interface ConsoleService {
    ConsoleResponse ajouterConsole(ConsoleTdo consoleTdo);
    void supprimerConsole(String idConsole);
    Console mettreAJourConsole(String idConsole,ConsoleTdo consoleTdo);
    ConsoleResponse findConsoleById(String idConsole);
    List<ConsoleResponse> findAllConsole();
    
}
