package com.patient.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.patient.serviceImpl.PatientServiceImpl;

@Service
public class Consumer {
	@Autowired
	private PatientServiceImpl patientServiceImpl;

	@KafkaListener(topics = "HospitalInfo", groupId = "group")
	public void consume(String hospitalId) {
		patientServiceImpl.deleteAllWithId(Integer.parseInt(hospitalId));
	}
}
