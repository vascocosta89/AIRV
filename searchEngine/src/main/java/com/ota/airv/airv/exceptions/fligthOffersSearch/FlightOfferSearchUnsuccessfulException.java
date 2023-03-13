package com.ota.airv.airv.exceptions.fligthOffersSearch;

public class FlightOfferSearchUnsuccessfulException extends RuntimeException {
    public FlightOfferSearchUnsuccessfulException() {
        super("No flights found!");
    }
}
