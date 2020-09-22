package com.example.contactsAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.contactsAPI.exception.ResourceNotFoundException;
import com.example.contactsAPI.model.Skill;
import com.example.contactsAPI.repository.SkillRepository;

@Service
@Transactional
public class SkillServiceImpl implements SkillService
{
	@Autowired
	private SkillRepository skillRepository;

	@Override
	public Skill createSkill(Skill skill) 
	{
		return skillRepository.save(skill);
	}

	@Override
	public Skill updateSkill(long id, Skill skill) 
	{
		Skill skillToUpdate = getSkillById(id);

		skillToUpdate.setName(skill.getName());
		skillToUpdate.setLevel(skill.getLevel());
		skillToUpdate.setContacts(skill.getContacts());

		return createSkill(skillToUpdate);
	}

	@Override
	public List<Skill> getAllSkills() 
	{
		return this.skillRepository.findAll();
	}

	@Override
	public Skill getSkillById(long id) 
	{
		return this.skillRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Skill not found with id "+id));
	}

	@Override
	public void deleteSkill(long id) 
	{
		Skill skill = getSkillById(id);
		this.skillRepository.delete(skill);
	}
}