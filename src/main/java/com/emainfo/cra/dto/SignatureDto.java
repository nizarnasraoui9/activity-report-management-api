package com.emainfo.cra.dto;

import com.emainfo.cra.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SignatureDto {
    private Long id ;
    private String signature;
    private String username;
}
