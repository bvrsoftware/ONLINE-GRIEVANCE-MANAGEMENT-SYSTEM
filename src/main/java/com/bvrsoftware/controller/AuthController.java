package com.bvrsoftware.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvrsoftware.exception.ApiException;
import com.bvrsoftware.payloads.JwtAuthRequest;
import com.bvrsoftware.payloads.JwtAuthResponse;
import com.bvrsoftware.payloads.UserDto;
import com.bvrsoftware.security.CustomUserDetails;
import com.bvrsoftware.security.JwtTokenHelper;
import com.bvrsoftware.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

	@Autowired
	private JwtTokenHelper helper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
		
		this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		String token = this.helper.generateToken(userDetails);
			JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
			jwtAuthResponse.setToken(token);
		return new ResponseEntity<JwtAuthResponse> (jwtAuthResponse,HttpStatus.OK);
	}


	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);	
		try {
			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			throw new ApiException("Invalid username and password");
		}
	}
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@Valid @RequestBody UserDto userDto){
		UserDto registeredUser=this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
	@GetMapping("/current-user")
	public ResponseEntity<CustomUserDetails> getCurrentUser(Principal principal) {
		return new ResponseEntity<CustomUserDetails>(((CustomUserDetails) this.userDetailsService.loadUserByUsername(principal.getName())),HttpStatus.OK);
	}
}