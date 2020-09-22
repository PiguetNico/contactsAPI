package com.example.contactsAPI.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.contactsAPI.exception.ResourceNotFoundException;
import com.example.contactsAPI.model.Contact;
import com.example.contactsAPI.repository.ContactRepository;

@Service
public class AuthenticateService implements UserDetailsService
{
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
	{
		Contact user = contactRepository.findByEmail(email);
		if(user == null) throw new ResourceNotFoundException("Email "+email+" not found");
		return new User(user.getEmail(), user.getPassword(), getGrantedAuthority(user));
	}
	
	private Collection<GrantedAuthority> getGrantedAuthority(Contact user)
	{
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if(user.getEmail().equalsIgnoreCase("admin@admin.com"))
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}
}