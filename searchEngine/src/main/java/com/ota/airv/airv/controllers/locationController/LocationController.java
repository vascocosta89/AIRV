package com.ota.airv.airv.controllers.locationController;

import com.ota.airv.airv.model.location.rest.LocationRest;
import com.ota.airv.airv.service.locationService.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/locationquery")
public class LocationController {

    @Autowired
    LocationService service;

    @GetMapping(path = "/{cityCode}")
    public LocationRest getLocations(@PathVariable String cityCode) {
        return service.getLocations(cityCode);
    }

}
