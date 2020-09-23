package com.example.contactsAPI.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "skill")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "contacts"})
public class Skill
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	private long id;
	@NotBlank(message = "Skill's name is mandatory")
	private String name;
	@PositiveOrZero
	private int level;
	@ApiModelProperty(hidden = true) 
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Contact.class)
	@JoinTable(
			name = "contact_skill",
			joinColumns = @JoinColumn(name = "contact_id"),
			inverseJoinColumns = @JoinColumn(name = "skill_id")
			)
	private Set<Contact> contacts = new HashSet<Contact>();
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
}