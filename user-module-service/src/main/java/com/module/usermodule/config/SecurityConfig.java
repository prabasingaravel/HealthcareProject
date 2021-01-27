package com.module.usermodule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.module.usermodule.filter.JwtFilter;
import com.module.usermodule.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public SecurityConfig(UserServiceImpl userServiceImpl, JwtFilter jwtFilter) {
		super();
		this.userServiceImpl = userServiceImpl;
		this.jwtFilter = jwtFilter;
	}
	
	private UserServiceImpl userServiceImpl;
	private JwtFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/users/authenticate")
			.permitAll().anyRequest().authenticated().and().exceptionHandling().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
