package com.microLearnByDoing.department_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Department {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
