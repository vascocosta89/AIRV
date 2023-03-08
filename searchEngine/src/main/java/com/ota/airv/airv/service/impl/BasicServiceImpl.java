package com.ota.airv.airv.service.impl;

import com.ota.airv.airv.exceptions.BasicNotFoundException;
import com.ota.airv.airv.model.BasicEntity;
import com.ota.airv.airv.model.rest.BasicRest;
import com.ota.airv.airv.repositories.BasicRepository;
import com.ota.airv.airv.service.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasicServiceImpl implements BasicService {

    @Autowired
    BasicRepository repository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public String getResponse() {
        return "Hello from AirV OTA. Happy flying!";
    }

    @Override
    public BasicRest createBasicEntity(BasicRest basicRest) {
        BasicEntity basicEntity = new BasicEntity();
        basicEntity.setStringToKeep(basicRest.getStringToKeep());
        basicEntity.setName(basicRest.getName());
        repository.save(basicEntity);
        BasicRest savedEntity = mapper.map(basicEntity, BasicRest.class);
        return savedEntity;
    }

    @Override
    public BasicRest getBasicEntityCreated(String name) {
        if(repository.findByName(name) == null){
            throw new BasicNotFoundException();
        }
        BasicEntity basicEntity = repository.findByName(name);
        BasicRest savedEntity = mapper.map(basicEntity, BasicRest.class);
        return savedEntity;
    }
}
