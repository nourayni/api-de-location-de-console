package com.consoletest.console.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consoletest.console.entitys.Client;

public interface ClientRepository extends JpaRepository<Client,String> {
    Optional<Client> findByNom(String name);
    List<Client> findByNomContaining(String nom);
}
