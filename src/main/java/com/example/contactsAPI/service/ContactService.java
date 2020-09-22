package com.example.contactsAPI.service;

import java.util.List;

import com.example.contactsAPI.model.Contact;

public interface ContactService 
{
	Contact createContact(Contact contact);
	
	Contact updateContact(long id, Contact contact);
	
	List<Contact> getAllContacts();
	
	Contact getContactById(long id);
	
	void deleteContact(long id);
	
	Contact addSkill(long idContact, long idSkill);
	
	Contact removeSkill(long idContact, long idSkill);
}