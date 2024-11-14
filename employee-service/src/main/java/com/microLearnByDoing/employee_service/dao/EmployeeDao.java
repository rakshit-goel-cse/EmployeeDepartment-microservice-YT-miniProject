package com.microLearnByDoing.employee_service.dao;

import com.microLearnByDoing.employee_service.entity.EmpEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDao {

	public EmployeeDao(EmpEntity ent) {
		this.name=ent.getName();
		this.age=ent.getAge();
		this.depId=ent.getDepId();
	}
	
	private String name;
	private int depId;
	private int age;
}
