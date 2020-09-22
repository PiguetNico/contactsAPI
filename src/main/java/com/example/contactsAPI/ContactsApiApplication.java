package com.example.contactsAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.contactsAPI.model.Contact;
import com.example.contactsAPI.repository.ContactRepository;

@SpringBootApplication
public class ContactsApiApplication implements CommandLineRunner
{
	@Autowired
	ContactRepository contactRepository;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(ContactsApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		Contact admin = new Contact();
		admin.setAdress("-");
		admin.setEmail("admin@admin.com");
		admin.setFirstName("-");
		admin.setFullName("-");
		admin.setLastName("-");
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
		admin.setPhoneNumber("0123456789");
		this.contactRepository.save(admin);
	}
}