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
import org.springframework.web.bind.annotation.RestController;

import com.example.contactsAPI.model.Skill;
import com.example.contactsAPI.service.SkillService;

import io.swagger.annotations.ApiOperation;

@RestController
public class SkillController 
{
	@Autowired
	private SkillService skillService;
	
	@ApiOperation(value = "Returns all skills")
	@GetMapping("/skills")
	public List<Skill> getAllSkills()
	{
		return skillService.getAllSkills();
	}
	
	@ApiOperation(value = "Return corresponding skill to id")
	@GetMapping("/skill/{id}")
	public Skill getSkillById(@PathVariable long id)
	{
		return skillService.getSkillById(id);
	}
	
	@ApiOperation(value = "Create a new skill")
	@PostMapping("/skill")
	public Skill createSkill(@Valid @RequestBody Skill skill)
	{
		return this.skillService.createSkill(skill);
	}
	
	@ApiOperation(value = "Update an existing skill")
	@PutMapping("/skill/{id}")
	public Skill updateSkill(@Valid @PathVariable long id, @RequestBody Skill skill)
	{
		return this.skillService.updateSkill(id,skill);
	}
	
	@ApiOperation(value = "Delete corresponding skill to id")
	@DeleteMapping("/skill/{id}")
	public HttpStatus deleteSkill(@PathVariable long id)
	{
		this.skillService.deleteSkill(id);
		return HttpStatus.OK;
	}
}