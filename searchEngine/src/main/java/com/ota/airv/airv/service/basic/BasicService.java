package com.ota.airv.airv.service.basic;

import com.ota.airv.airv.model.basic.rest.BasicRest;

public interface BasicService {
    String getResponse();
    BasicRest createBasicEntity(BasicRest basicRest);
    BasicRest getBasicEntityCreated(String name);
}
