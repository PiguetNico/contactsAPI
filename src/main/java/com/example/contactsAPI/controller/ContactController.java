package com.example.contactsAPI.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.contactsAPI.model.Contact;
import com.example.contactsAPI.service.ContactService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ContactController 
{
	@Autowired
	private ContactService contactService;
	
	@ApiOperation(value = "Returns all contacts with their skill(s)")
	@GetMapping("/contacts")
	public List<Contact> getAllContact()
	{
		return this.contactService.getAllContacts();
	}

	@ApiOperation(value = "Return corresponding contact to id")
	@GetMapping("/contact/{id}")
	public Contact getContactById(@PathVariable long id)
	{
		return this.contactService.getContactById(id);
	}
	
	@ApiOperation(value = "Create a new contact")
	@PostMapping("/contact")
	public Contact createContact(@Valid @RequestBody Contact contact)
	{
		return this.contactService.createContact(contact);
	}
	
	@ApiOperation(value = "Update an existing contact")
	@PutMapping("/contact/{id}")
	public Contact updateContact(@Valid @PathVariable long id, @RequestBody Contact contact)
	{
		return this.contactService.updateContact(id, contact);
	}
	
	@ApiOperation(value = "Delete corresponding contact to id")
	@DeleteMapping("/contact/{id}")
	public HttpStatus deleteContact(@PathVariable long id)
	{
		this.contactService.deleteContact(id);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Add an existing skill to an existing contact")
	@PutMapping("/add")
	public Contact addSkill(@RequestParam long idContact, @RequestParam long idSkill)
	{
		return this.contactService.addSkill(idContact, idSkill);
	}
	
	@ApiOperation(value = "Remove a skill from a contact")
	@PutMapping("/remove")
	public Contact removeSkill(@RequestParam long idContact, @RequestParam long idSkill)
	{
		return this.contactService.removeSkill(idContact, idSkill);
	}
}