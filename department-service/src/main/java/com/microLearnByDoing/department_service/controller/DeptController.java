package com.microLearnByDoing.department_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microLearnByDoing.department_service.client.EmployeeClient;
import com.microLearnByDoing.department_service.custon_annotation.FlowLogger;
import com.microLearnByDoing.department_service.dao.DepartmentDAO;
import com.microLearnByDoing.department_service.dao.DepartmentDaoJsonBuilder;
import com.microLearnByDoing.department_service.entity.Department;
import com.microLearnByDoing.department_service.repository.DepartmentRepo;
import com.microLearnByDoing.department_service.service.DeleteDepEmpService;

@RestController
@RequestMapping("/department")
public class DeptController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DepartmentRepo depRepo;
	@Autowired
	DepartmentDaoJsonBuilder depDaoBulder;
	
	@Autowired
	private EmployeeClient empClient;
	
	@Autowired
	DeleteDepEmpService deleteService;
	
	@GetMapping("/getAllDept")
	public ResponseEntity<List<DepartmentDAO>> getAllDept() {
		logger.trace("Entering Method - getAllDept",this.getClass());
		logger.debug("fetching data");
		
		logger.debug("Setting data to dao");
		
		List<DepartmentDAO> resData= depRepo.findAll().stream()
				.map(dep->
						depDaoBulder
							.setId(dep.getId())
							.setName(dep.getName())
							.setEmployees(
									empClient.getDeptEmp(dep.getId())
									)
							.build()
					)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<DepartmentDAO>>(resData,HttpStatus.FOUND);
	}
	
	@FlowLogger
	@PostMapping("/addDept")
	public ResponseEntity<Department> addDepartment(@RequestBody Department dept) {
		logger.debug("Saving dept data- "+dept);
		depRepo.save(dept);
		
		return new ResponseEntity<Department>(dept,HttpStatus.CREATED);
	}
	
	@FlowLogger
	@DeleteMapping("/dept")
	public ResponseEntity deleteDep(@RequestParam(name="id") int id) {
		
		try {
			depRepo.deleteById(id);
			deleteService.deleteEmpForDep(id);
			return new ResponseEntity<Boolean>(true,HttpStatus.GONE);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.toString(),HttpStatus.CONFLICT);
		}
	}
}
