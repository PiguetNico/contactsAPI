package com.example.contactsAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private AuthenticationEntryPoint entryPoint;
	
	@Autowired
	private AuthenticateService authenticateService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(authenticateService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeRequests().antMatchers(
				 "/", "/csrf", 
               "/v2/api-docs", 
               "/swagger-resources/**",
               "/swagger-ui.html",
               "/webjars/**"
                ).permitAll().anyRequest().authenticated()
		.and().httpBasic().authenticationEntryPoint(entryPoint);
	}
}