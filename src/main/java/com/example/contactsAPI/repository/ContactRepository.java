package com.example.contactsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.contactsAPI.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>
{
	@Query("FROM Contact WHERE email=:email")
	Contact findByEmail(@Param("email") String email);
}