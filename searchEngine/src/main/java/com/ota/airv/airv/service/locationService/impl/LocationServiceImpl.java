package com.ota.airv.airv.service.locationService.impl;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.ota.airv.airv.exceptions.location.LocationNotFoundException;
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
    public String getLocations(String cityCode)  {
        this.verifyClient();

        LocationEntity locationEntity = repository.findByIataCode(cityCode);

        if(!Objects.isNull(locationEntity)){
            return locationEntity.toString();
        }

        // Get a specific city or airport based on its id
        Location location = null;

        try {
            location = client.referenceData
                    .location(cityCode.toUpperCase()).get();
        } catch (ResponseException e) {
            throw new LocationNotFoundException();
        }

        this.statusCodeValidation(location);
        this.saveToDatabase(location);

        return location.toString();
    }

    private void saveToDatabase(Location location) {
        if(Objects.isNull(repository.findByIataCode(location.getIataCode()))) {
            LocationEntity locationEntityToSave = modelMapper.map(location, LocationEntity.class);
            repository.save(locationEntityToSave);
        }
    }

    private void statusCodeValidation(Location location) {
        if (location.getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + location.getResponse().getStatusCode());
            System.exit(-1);
        }
    }

    private void verifyClient(){
        if(Objects.isNull(client)){
            client = auth.getAmadeus(amadeusKey,amadeusSecret);
        }
    }
}
