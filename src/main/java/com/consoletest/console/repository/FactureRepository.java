package com.consoletest.console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consoletest.console.entitys.Facture;

public interface FactureRepository extends JpaRepository<Facture,String> {
    
}
