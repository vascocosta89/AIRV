package com.ota.airv.airv.service.locationService.impl;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.ota.airv.airv.exceptions.location.LocationNotFoundException;
import com.ota.airv.airv.model.location.LocationEntity;
import com.ota.airv.airv.model.location.rest.LocationRest;
import com.ota.airv.airv.repositories.location.LocationRepository;
import com.ota.airv.airv.service.locationService.LocationService;
import com.ota.airv.airv.utils.AuthorizationAmadeus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    AuthorizationAmadeus auth;

    @Autowired
    LocationRepository repository;

    private Amadeus client;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public LocationRest getLocations(String cityCode)  {
        this.verifyClient();

        LocationEntity locationEntity = repository.findByIataCode(cityCode);

        if(!Objects.isNull(locationEntity)){
            return modelMapper.map(locationEntity,LocationRest.class);
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

        LocationRest returnObject = modelMapper.map(location,LocationRest.class);

        return returnObject;
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
            client = auth.getAmadeus();
        }
    }
}
