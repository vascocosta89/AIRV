package com.ota.airv.airv.controllers.locationController;

import com.amadeus.exceptions.ResponseException;
import com.ota.airv.airv.service.locationService.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/flightquery")
public class LocationController {

    @Autowired
    LocationService service;

    @GetMapping(path = "/simple")
    public String getLocations() throws ResponseException {
        return service.getLocations();
    }

}
