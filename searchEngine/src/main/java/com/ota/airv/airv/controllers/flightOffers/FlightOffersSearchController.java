package com.ota.airv.airv.controllers.flightOffers;

import com.amadeus.resources.FlightOfferSearch;
import com.ota.airv.airv.service.flightOffersSearchService.FlightOffersSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/flightoffersseach")
public class FlightOffersSearchController {

    @Autowired
    FlightOffersSearchService service;
    @GetMapping("/flightofferssearch")
    public List<FlightOfferSearch> getFlightsSearch(@RequestParam(required=true) String origin,
                                                    @RequestParam(required=true) String destination,
                                                    @RequestParam(required=true) String departDate,
                                                    @RequestParam(required=true) String adults,
                                                    @RequestParam(required = false) String returnDate){
      return service.doFlightsSearch(origin, destination, departDate, adults, returnDate);
    }

}
