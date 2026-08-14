package com.snnydn.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String street;
    private String postalCode;

}
