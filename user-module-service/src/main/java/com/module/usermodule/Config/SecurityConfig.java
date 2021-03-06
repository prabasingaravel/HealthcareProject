package com.module.usermodule.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.module.usermodule.Filter.JwtFilter;
import com.module.usermodule.ServiceImpl.UserServiceImpl;

/**
 * SecurityConfig is used to authenticate the end point hits.
 * @author Praba Singaravel
 * @since 21.02
 *
 */	
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserServiceImpl userServiceImpl;
	private final JwtFilter jwtFilter;
	
	@Lazy
	@Autowired
	public SecurityConfig(UserServiceImpl userServiceImpl, JwtFilter jwtFilter) {
		this.userServiceImpl = userServiceImpl;
		this.jwtFilter = jwtFilter;
	}
	
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
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/users/authenticate", "/actuator/**", "/beans/reset", "/swagger**").permitAll()
			.anyRequest().authenticated().and().exceptionHandling().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		List<String> allowedMethods = Collections.unmodifiableList(Arrays.asList(HttpMethod.GET.name(),
				HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
		CorsConfiguration otherConfig = new CorsConfiguration();
		otherConfig.setAllowedMethods(allowedMethods);
		source.registerCorsConfiguration("/**",
				new CorsConfiguration().applyPermitDefaultValues().combine(otherConfig));
		return source;
	}
}
