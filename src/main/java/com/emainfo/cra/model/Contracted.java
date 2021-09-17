package com.emainfo.cra.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contracted {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "contracted")
    @JsonIgnore
    List<Cra> craList;

}
