package com.ota.airv.airv.exceptions.location;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException() {
        super("Location not found!");
    }
}
