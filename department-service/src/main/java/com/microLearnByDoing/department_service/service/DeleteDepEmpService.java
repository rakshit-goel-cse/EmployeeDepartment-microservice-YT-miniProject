package com.microLearnByDoing.department_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeleteDepEmpService {
	
	//Kafka template help storing data to kafka clustur;
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public boolean deleteEmpForDep(int depId) throws Exception {
		
		kafkaTemplate.send("dept-delete",String.valueOf(depId));
		return true;
	}

}
