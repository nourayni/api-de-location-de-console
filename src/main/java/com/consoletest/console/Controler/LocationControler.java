package com.consoletest.console.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consoletest.console.dto.LocationRequest;
import com.consoletest.console.dto.LocationResponse;
import com.consoletest.console.service.location.LocationService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/location")
@AllArgsConstructor
@RequiredArgsConstructor
public class LocationControler {
    @Autowired
    private LocationService locationService;

    @PostMapping("/")
    public ResponseEntity<LocationResponse> createLocation(@RequestBody LocationRequest locationRequest){
        LocationResponse locationResponse = locationService.createLocation(locationRequest);
        return new ResponseEntity<LocationResponse>(locationResponse, HttpStatus.CREATED);
    }
}
