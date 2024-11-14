package com.microLearnByDoing.employee_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microLearnByDoing.employee_service.dao.EmployeeDao;
import com.microLearnByDoing.employee_service.entity.EmpEntity;
import com.microLearnByDoing.employee_service.repository.EmpRepo;

@RestController
@RequestMapping("/employee")
public class EmpController {

	@Autowired
	EmpRepo empRepo;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<EmployeeDao>> getAllEmployees(){
		List<EmployeeDao> res=empRepo.findAll().stream()
				.map(req->new EmployeeDao(req.getName(),req.getAge(),req.getDepId()))
				.collect(Collectors.toList());
		return new ResponseEntity<List<EmployeeDao>>(res,HttpStatus.FOUND);
	}
	
	@PostMapping("/addEmp")
	public ResponseEntity<EmployeeDao> addData(@RequestBody EmployeeDao body) {
		logger.debug("Request recived- ",body);
		
		EmpEntity enti=new EmpEntity();
		enti.setAge(body.getAge());
		enti.setDepId(body.getDepId());
		enti.setName(body.getName());
		empRepo.save(enti);
		
		return new ResponseEntity<EmployeeDao>(body,HttpStatus.CREATED);
	}
	
	@GetMapping("/getEmp/{id}")
	public ResponseEntity<EmployeeDao> getEmp(@PathVariable(name = "id") int id){
		logger.debug("Request for specific emp");
		
		return empRepo.findById(String.valueOf(id))
		        .map(empEntity -> new ResponseEntity<>(new EmployeeDao(empEntity), HttpStatus.OK))
		        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/getDepEmp/{depId}")
	public ResponseEntity<List<EmployeeDao>> getDeptEmp(@PathVariable(name="depId") int id){
		logger.debug("Fetching all emp by dep id");
		
		return new ResponseEntity<List<EmployeeDao>>(
					empRepo.findByDepId(id).stream()
						.map(ent->new EmployeeDao(ent))
						.collect(Collectors.toList())
					,HttpStatus.FOUND
				);
	}
	
	public void deleteDept(int id) {
		logger.info("deleting dept from employees for id- "+id);
		empRepo.saveAllAndFlush(
				empRepo.findByDepId(id)
				.stream()
				.peek(ent->ent.setDepId(0))
				.toList()
			);
	}
	
}
