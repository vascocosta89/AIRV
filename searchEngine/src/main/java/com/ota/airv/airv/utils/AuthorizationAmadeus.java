package com.ota.airv.airv.utils;

import com.amadeus.Amadeus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorizationAmadeus {

    @Value("${application.amadeus.key}")
    private String amadeusKey;

    @Value("${application.amadeus.secret}")
    private String amadeusSecret;

    private Amadeus amadeus;

    public Amadeus getAmadeus() { //todo add timeout control feature
        if(Objects.isNull(amadeus)){
        return Amadeus
                .builder(amadeusKey, amadeusSecret)
                .build();
        }
        return amadeus;
    }
}
