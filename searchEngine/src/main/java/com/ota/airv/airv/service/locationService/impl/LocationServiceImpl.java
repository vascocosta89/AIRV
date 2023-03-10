package com.ota.airv.airv.service.locationService.impl;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.ota.airv.airv.model.location.LocationEntity;
import com.ota.airv.airv.repositories.location.LocationRepository;
import com.ota.airv.airv.service.locationService.LocationService;
import com.ota.airv.airv.utils.AuthorizationAmadeus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LocationServiceImpl implements LocationService {

    @Value("${application.amadeus.key}")
    private String amadeusKey;

    @Value("${application.amadeus.secret}")
    private String amadeusSecret;

    @Autowired
    AuthorizationAmadeus auth;

    Amadeus client;

    @Autowired
    LocationRepository repository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public String getLocations() throws ResponseException {
        verifyClient();
        // Get a specific city or airport based on its id
        Location location = client.referenceData
                .location("ALGW").get();

        if (location.getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + location.getResponse().getStatusCode());
            System.exit(-1);
        }

        if(Objects.isNull(repository.findByIataCode(location.getIataCode()))) {
            LocationEntity locationEntity = modelMapper.map(location, LocationEntity.class);
            repository.save(locationEntity);
        }

        return location.getName();
    }

    private void verifyClient(){
        if(Objects.isNull(client)){
            client = auth.getAmadeus(amadeusKey,amadeusSecret);
        }
    }
}
