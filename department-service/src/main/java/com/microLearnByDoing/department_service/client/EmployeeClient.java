package com.microLearnByDoing.department_service.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.microLearnByDoing.department_service.dao.Employee;

@HttpExchange
public interface EmployeeClient {

	@GetExchange("employee/getDepEmp/{depId}")
	public List<Employee> getDeptEmp(@PathVariable(name="depId") int id);
}
