package com.example.contactsAPI.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.contactsAPI.serializer.CustomSkillSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "contact")
public class Contact 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true) 
	private long id;
	@NotBlank(message = "Firstname is mandatory")
	private String firstName;
	@NotBlank(message = "Lastname is mandatory")
	private String lastName;
	private String fullName;
	private String adress;
	@Email
	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "Password is mandatory")
	private String password;
	@Pattern(regexp="(^$|[0-9]{10})", message = "Your number must contains 10 digits")
	private String phoneNumber;
	@ApiModelProperty(hidden = true) 
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "contact_skill",
			joinColumns = @JoinColumn(name = "contact_id"),
			inverseJoinColumns = @JoinColumn(name = "skill_id")
			)
	@JsonSerialize(using = CustomSkillSerializer.class)
	private List<Skill> skills = new ArrayList<Skill>();

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
	}
	
	public void removeSkill(Skill skill) {
		skills.remove(skill);
	}
}