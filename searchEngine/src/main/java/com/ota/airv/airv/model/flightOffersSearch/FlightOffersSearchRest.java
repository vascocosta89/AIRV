package com.ota.airv.airv.model.flightOffersSearch;

import com.amadeus.resources.FlightOfferSearch;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlightOffersSearchRest {

    private String type;
    private String id;
    private String source;
    private boolean instantTicketingRequired;
    private boolean disablePricing;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private  boolean paymentCardRequired;
    private String lastTicketingDate;
    private  int numberOfBookableSeats;
    //    private  FlightOfferSearch.Itinerary[] itineraries;
    //    private  FlightOfferSearch.SearchPrice price;
    //    private  FlightOfferSearch.PricingOptions pricingOptions;
    //    private  FlightOfferSearch.TravelerPricing[] travelerPricings;
    private  String[] validatingAirlineCodes;
    private  String choiceProbability;
    private  FlightOfferSearch.FareRules fareRules;
}
