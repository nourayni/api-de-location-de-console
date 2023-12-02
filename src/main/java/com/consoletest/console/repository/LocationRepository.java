package com.consoletest.console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consoletest.console.entitys.Location;

public interface LocationRepository extends JpaRepository<Location,String> {
    
}
