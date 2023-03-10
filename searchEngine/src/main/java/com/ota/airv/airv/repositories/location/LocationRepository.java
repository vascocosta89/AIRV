package com.ota.airv.airv.repositories.location;

import com.ota.airv.airv.model.location.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository
        extends JpaRepository<LocationEntity,Long> {

    LocationEntity findByIataCode(String iataCode);

}
