package com.ota.airv.airv.service.locationService;

import com.ota.airv.airv.model.location.rest.LocationRest;

public interface LocationService {

    LocationRest getLocations(String cityCode);

}
