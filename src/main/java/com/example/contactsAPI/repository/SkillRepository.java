package com.example.contactsAPI.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contactsAPI.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{} 