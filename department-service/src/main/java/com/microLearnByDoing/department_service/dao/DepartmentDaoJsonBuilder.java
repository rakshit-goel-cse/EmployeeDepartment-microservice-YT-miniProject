package com.microLearnByDoing.department_service.dao;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DepartmentDaoJsonBuilder {

	private DepartmentDaoJsonBuilder() {};
	
	private int id;
	private String name;
	private List<Employee> employees;
	
	public DepartmentDaoJsonBuilder setId(int id) {
		this.id=id;
		return this;
	}
	
	public DepartmentDaoJsonBuilder setName(String name) {
		this.name=name;
		return this;
	}
	
	public DepartmentDaoJsonBuilder setEmployees(List<Employee> emp) {
		this.employees=emp;
		return this;
	}
	
	public DepartmentDAO build() {
		return new DepartmentDAO(this.id,this.name,this.employees);
	}

}
