package com.emainfo.cra.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAccountDto {
	private String username;
	private String password;
}
