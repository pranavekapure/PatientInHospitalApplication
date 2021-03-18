package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.pojo.Patient;
import com.patient.serviceImpl.PatientServiceImpl;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientServiceImpl patientServiceImpl;

	@GetMapping(path = "/getPatientList")
	public ResponseEntity<List<Patient>> getPatientInfo() {

		return new ResponseEntity<List<Patient>>(patientServiceImpl.getPatientInfo(), HttpStatus.OK);
	}

	@PostMapping(path = "/savePatient")
	public ResponseEntity<Patient> saveOrUpdatePatientInfo(@RequestBody Patient patient) {

		return new ResponseEntity<Patient>(patientServiceImpl.saveOrUpdatePatientInfo(patient), HttpStatus.OK);
	}


	@GetMapping(path = "/getPatient/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable Integer id) {

		return new ResponseEntity<>(patientServiceImpl.getPatient(id).get(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deletePatient/{patientId}")
	public ResponseEntity<String> deletePatientInfo(@PathVariable Integer patientId) {

		return new ResponseEntity<>(patientServiceImpl.deletePatient(patientId), HttpStatus.OK);
	}
	
	@GetMapping(path = "/getPatients/{hospitalId}")
	public ResponseEntity<List<Patient>> getPatients(@PathVariable Integer hospitalId) {

		return new ResponseEntity<>(patientServiceImpl.getPatientsWithId(hospitalId), HttpStatus.OK);
	}
	
	@GetMapping(path = "/deletePatients/{hospitalId}")
	public ResponseEntity<String> deletePatients(@PathVariable Integer hospitalId) {

		return new ResponseEntity<>(patientServiceImpl.deleteAllWithId(hospitalId), HttpStatus.OK);
	}

}
