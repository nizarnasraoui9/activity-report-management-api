package com.emainfo.cra.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDay {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int dayOfMonth;
    private String dayName ;
    private String workedTime;
    private int workedSeconds;
    @ManyToOne
    private Cra cra;

}
