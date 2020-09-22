package com.example.contactsAPI.service;

import java.util.List;

import com.example.contactsAPI.model.Skill;

public interface SkillService 
{
	Skill createSkill(Skill skill);
	
	Skill updateSkill(long id, Skill skill);
	
	List<Skill> getAllSkills();
	
	Skill getSkillById(long id);
	
	void deleteSkill(long id);
}