package com.microLearnByDoing.employee_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microLearnByDoing.employee_service.controller.EmpController;

@Service
public class KafkaListner {

	@Autowired
	EmpController controller;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@KafkaListener(topics = "dept-delete",groupId="user-group")
	public void removeDeptForEmp(int id) {
		logger.debug("Entring delete dept from emp");
		controller.deleteDept(id);
		logger.debug("Exiting delete dept from emp");
	}
}
