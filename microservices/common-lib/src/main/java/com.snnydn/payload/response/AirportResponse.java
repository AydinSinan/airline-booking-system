package com.snnydn.payload.response;

import com.snnydn.embeddable.Address;
import com.snnydn.embeddable.GeoCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirportResponse {

    private Long id;
    private String iataCode;
    private String name;
    private String detailedName;
    private ZoneId zoneId;
    private Address address;
    private CityResponse cityResponse;
    private GeoCode geoCode;
}
