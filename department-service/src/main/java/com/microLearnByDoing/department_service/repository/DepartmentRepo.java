package com.microLearnByDoing.department_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microLearnByDoing.department_service.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	
	

}
