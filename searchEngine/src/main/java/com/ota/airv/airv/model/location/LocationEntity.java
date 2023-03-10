package com.ota.airv.airv.model.location;

import com.amadeus.resources.Location;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "location_entity")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private String subType;
    private String name;
    private String detailedName;
    private String timeZoneOffset;
    private String iataCode;
//    private Location.GeoCode geoCode;
//    private Location.Address address;
//    private Location.Distance distance;
//    private Location.Analytics analytics;
    private Double relevance;

}
