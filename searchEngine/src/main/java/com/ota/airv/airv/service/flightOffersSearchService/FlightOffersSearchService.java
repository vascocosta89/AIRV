package com.ota.airv.airv.service.flightOffersSearchService;

import com.amadeus.resources.FlightOfferSearch;

import java.util.List;

public interface FlightOffersSearchService {
    List<FlightOfferSearch> doFlightsSearch(String origin, String destination, String departDate, String adults, String returnDate);
}
