package com.module.usermodule.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.module.usermodule.dto.AuthenticateDto;
import com.module.usermodule.util.JwtUtil;

@Service
public class TokenManager {
	@Autowired
	public TokenManager(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	
	public AuthenticateDto generateToken(@RequestBody AuthenticateDto authenticateDto) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticateDto.getUserName(), authenticateDto.getPassword())
			);
		} catch (Exception exception) {
			throw new Exception("Invalid username/password");
		}
		String token = jwtUtil.generateToken(authenticateDto.getUserName());
		authenticateDto.setToken(token);
		authenticateDto.setPassword(null);
		return authenticateDto;
	}
}
