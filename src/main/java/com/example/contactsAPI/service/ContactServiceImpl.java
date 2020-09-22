package com.example.contactsAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.contactsAPI.exception.ResourceNotFoundException;
import com.example.contactsAPI.model.Contact;
import com.example.contactsAPI.model.Skill;
import com.example.contactsAPI.repository.ContactRepository;
import com.example.contactsAPI.repository.SkillRepository;

@Service
@Transactional
public class ContactServiceImpl implements ContactService 
{
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private SkillRepository skillRepository;

	@Override
	public Contact createContact(Contact contact) 
	{
		return this.contactRepository.save(contact);
	}

	@Override
	public Contact updateContact(long id, Contact contact) 
	{	
		Contact contactToUpdate = getContactById(id);
		
		contactToUpdate.setFirstName(contact.getFirstName());
		contactToUpdate.setLastName(contact.getLastName());
		contactToUpdate.setFullName(contact.getFullName());
		contactToUpdate.setAdress(contact.getAdress());
		contactToUpdate.setEmail(contact.getEmail());
		contactToUpdate.setPhoneNumber(contact.getPhoneNumber());
		contactToUpdate.setPassword(contact.getPassword());
		contactToUpdate.setSkills(contact.getSkills());
		
		return this.contactRepository.save(contactToUpdate);	
	}

	@Override
	public List<Contact> getAllContacts() 
	{
		return this.contactRepository.findAll();
	}

	@Override
	public Contact getContactById(long id) 
	{
		return this.contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found with id "+id));
	}

	@Override
	public void deleteContact(long id) 
	{
		Contact contact = getContactById(id);
		this.contactRepository.delete(contact);
	}

	@Override
	public Contact addSkill(long idContact, long idSkill) 
	{
		Contact contact = getContactById(idContact);
		Skill skill = this.skillRepository.findById(idSkill)
				.orElseThrow(() -> new ResourceNotFoundException("Skill not found with id "+idSkill));
		
		if(contact.getSkills().contains(skill)) throw new ResourceNotFoundException("Skill "+idSkill +" is already assigned to contact "+ idContact);

		contact.addSkill(skill);
		this.contactRepository.save(contact);
		return contact;
	}

	@Override
	public Contact removeSkill(long idContact, long idSkill) 
	{
		Contact contact = getContactById(idContact);
		Skill skill = this.skillRepository.findById(idSkill)
				.orElseThrow(() -> new ResourceNotFoundException("Skill not found with id "+idSkill));
		
		if(!contact.getSkills().contains(skill)) throw new ResourceNotFoundException("Skill "+idSkill +" is not assigned to contact "+ idContact);
		
		contact.removeSkill(skill);
		return contact;
	}
}