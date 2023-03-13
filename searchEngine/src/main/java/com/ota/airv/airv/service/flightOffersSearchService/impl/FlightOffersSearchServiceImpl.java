package com.ota.airv.airv.service.flightOffersSearchService.impl;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ota.airv.airv.exceptions.fligthOffersSearch.FlightOfferSearchUnsuccessfulException;
import com.ota.airv.airv.service.flightOffersSearchService.FlightOffersSearchService;
import com.ota.airv.airv.utils.AuthorizationAmadeus;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class FlightOffersSearchServiceImpl implements FlightOffersSearchService {

    @Autowired
    AuthorizationAmadeus auth;

    private Amadeus client;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<FlightOfferSearch> doFlightsSearch(String origin, String destination, String departDate, String adults, String returnDate) {
        verifyClient();
        FlightOfferSearch[] queriedFlights = null;
        try {
            queriedFlights = client.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", origin)
                            .and("destinationLocationCode", destination)
                            .and("departureDate", departDate)
                            .and("returnDate", returnDate)
                            .and("adults", adults)
                            .and("max", 3));
        } catch (ResponseException e) {
            throw new FlightOfferSearchUnsuccessfulException();
        }

        String json = new Gson().toJson(queriedFlights);
        Type type = new TypeToken<List<FlightOfferSearch>>(){}.getType();
        List<FlightOfferSearch> inpList = new Gson().fromJson(json, type);

        return inpList;
    }

    private void verifyClient() {
        if (Objects.isNull(client)) {
            client = auth.getAmadeus();
        }
    }
}
