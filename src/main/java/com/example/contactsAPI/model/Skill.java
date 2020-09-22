package com.example.contactsAPI.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.example.contactsAPI.serializer.CustomContactSerlializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "skill")
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
	@ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
	@JsonSerialize(using = CustomContactSerlializer.class)
	private List<Contact> contacts = new ArrayList<Contact>();
	
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
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}