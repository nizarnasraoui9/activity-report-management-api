package com.emainfo.cra.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cra {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private Integer year;
    private Integer month;
    private String note;
    private String createdAt;
    private String updatedAt;
    private String local;
    @OneToMany(cascade=CascadeType.ALL)
    private List<WorkDay> workDays;
    @ManyToOne()
    private Contracted contracted;
    @ManyToOne()
    private Client client;
    
    @ManyToOne()
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

   
}
