package com.emainfo.cra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Signature {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String signature ;
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;



}
