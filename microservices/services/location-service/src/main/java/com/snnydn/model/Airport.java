package com.snnydn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snnydn.embeddable.Address;
import com.snnydn.embeddable.GeoCode;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 3)
    private String iataCode;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private String name;

    @Embedded
    private GeoCode geoCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZone;

    @ManyToOne
    @JsonIgnore
    private City city;

    @JsonIgnore
    @Transient
    public String getDetailedName() {
        if(city != null && city.getCountryCode() != null) {
            return name.toUpperCase() + "/" + city.getCityCode();
        }
        return name.toUpperCase();
    }
}
