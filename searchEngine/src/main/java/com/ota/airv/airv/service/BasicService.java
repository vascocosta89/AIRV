package com.ota.airv.airv.service;

import com.ota.airv.airv.model.rest.BasicRest;

public interface BasicService {
    String getResponse();
    BasicRest createBasicEntity(BasicRest basicRest);
    BasicRest getBasicEntityCreated(String name);
}
