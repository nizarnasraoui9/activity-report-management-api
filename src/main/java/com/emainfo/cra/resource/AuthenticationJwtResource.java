package com.emainfo.cra.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emainfo.cra.dto.AccountDto;
import com.emainfo.cra.dto.JwtAccountDto;
import com.emainfo.cra.dto.JwtResponseDto;
import com.emainfo.cra.exception.CraException;
import com.emainfo.cra.service.AccountService;
import com.emainfo.cra.util.JwtTokenUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name="Authentication")
@CrossOrigin()
public class AuthenticationJwtResource {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final AccountService accountService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody final JwtAccountDto authenticationRequest){
		final Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponseDto(token));
	}

	private Authentication authenticate(final String username, final String password) {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (final DisabledException e) {
			throw new CraException("USER_DISABLED", e);
		} catch (final BadCredentialsException e) {
			throw new CraException("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<Mono<AccountDto>> saveUser(@RequestBody final AccountDto user) throws Exception {
		return ResponseEntity.ok(accountService.save(user));
	}
}
