package com.module.usermodule.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.module.usermodule.ServiceImpl.UserServiceImpl;
import com.module.usermodule.Util.JwtUtil;

/**
 * JwtFilter is used for custom authentication.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Component
public class JwtFilter extends OncePerRequestFilter{

	private JwtUtil jwtUtil;
	private UserServiceImpl userServiceImpl;
	
	@Lazy
	@Autowired
	public JwtFilter(JwtUtil jwtUtil,UserServiceImpl userServiceImpl) {
		this.jwtUtil = jwtUtil;
		this.userServiceImpl = userServiceImpl;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		String token=null;
		String userName=null;
		if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(token);
		}
		if(userName!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userServiceImpl.loadUserByUsername(userName);
			if(jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
