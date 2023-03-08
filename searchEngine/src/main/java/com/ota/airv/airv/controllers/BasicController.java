package com.ota.airv.airv.controllers;

import com.ota.airv.airv.model.rest.BasicRest;
import com.ota.airv.airv.service.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/test")
public class BasicController {

    @Autowired
    BasicService service;

    @GetMapping
    public String returnTestString(){
        String response = service.getResponse();
        log.info("Test OK: {}",response);
        return response;
    }

    @PostMapping
    public BasicRest createBasicEntity(@RequestBody BasicRest basicRest){
        BasicRest basicRestSaved = service.createBasicEntity(basicRest);
        return basicRestSaved;
    }

    @GetMapping(path = "/{name}")
    public BasicRest getBasicEntity(@PathVariable String name){
        BasicRest basicRestSaved = service.getBasicEntityCreated(name);
        return basicRestSaved;
    }

}
