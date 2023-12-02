package com.consoletest.console.dto;

import com.consoletest.console.enumeration.EtatConsole;


public record ConsoleTdo (
    Long idConsole,
    String nameConsole,
    Long prixDeLocationParJour,
    String model,
    String numeroSerie,
    EtatConsole etatConsole
){

}
