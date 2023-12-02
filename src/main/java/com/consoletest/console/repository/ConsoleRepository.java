package com.consoletest.console.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consoletest.console.entitys.Console;

public interface ConsoleRepository extends JpaRepository<Console,String> {
    Optional<Console> findByNumeroSerie(String name);
}
