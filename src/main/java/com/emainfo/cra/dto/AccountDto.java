package com.emainfo.cra.dto;

import com.emainfo.cra.model.Signature;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {
	private Long accountId ;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private String avatar ;

}
