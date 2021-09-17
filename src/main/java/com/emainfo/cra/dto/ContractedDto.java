package com.emainfo.cra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContractedDto {
    private long id;
    private String name;
    private String siret;
    private String uniqueIdentifier;
    private String country;
    private String streetNumber;
    private String repetition;
    private String streetType;
    private String streetName;
    private String postalCode;
    private String city;
    private String contact;
}

