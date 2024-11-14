package com.microLearnByDoing.employee_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microLearnByDoing.employee_service.entity.EmpEntity;

public interface EmpRepo extends JpaRepository<EmpEntity, String> {
	
	public List<EmpEntity> findByDepId(int id);
}
