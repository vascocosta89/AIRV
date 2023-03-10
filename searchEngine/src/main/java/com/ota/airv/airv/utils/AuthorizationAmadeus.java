package com.ota.airv.airv.utils;

import com.amadeus.Amadeus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Objects;

@Component
public class AuthorizationAmadeus {

    public Amadeus getAmadeus(String amadeusKey, String amadeusSecret) {
        return Amadeus
                .builder(amadeusKey, amadeusSecret)
                .build();
    }
}
