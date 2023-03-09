package com.ota.airv.airv.controllers.flightQuery;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.ota.airv.airv.service.flightQuery.FlightQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/flightquery")
public class FlightQueryController {

    @Value("${application.amadeus.key}")
    private String amadeusKey;

    @Value("${application.amadeus.secret}")
    private String amadeusSecret;

    @Autowired
    FlightQueryService service;


    @GetMapping(path = "/simple")
    public String getLocations() throws ResponseException {
        Amadeus amadeus = Amadeus
                .builder(amadeusKey, amadeusSecret)
                .build();

        // Get a specific city or airport based on its id
        Location location = amadeus.referenceData
                .location("ALHR").get();

        if(location.getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + location.getResponse().getStatusCode());
            System.exit(-1);
        }

        System.out.println(location);
        return location.getName();
    }
}
