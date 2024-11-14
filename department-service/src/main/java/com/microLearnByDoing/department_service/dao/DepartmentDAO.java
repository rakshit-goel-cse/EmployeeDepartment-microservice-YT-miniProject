package com.microLearnByDoing.department_service.dao;

import java.util.List;

public class DepartmentDAO {

	public DepartmentDAO(int id,String name, List<Employee> emp){
		this.id=id;
		this.name=name;
		this.employees=emp;
	}
	
	private int id;
	private String name;
	private List<Employee> employees;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
