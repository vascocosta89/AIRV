package com.ota.airv.airv.model.location.rest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationRest {

    private String type;
    private String subType;
    private String name;
    private String detailedName;
    private String timeZoneOffset;
    private String iataCode;

}
