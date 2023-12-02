package com.consoletest.console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consoletest.console.entitys.Images;

public interface ImagesRepository extends JpaRepository<Images,String> {
    
}
