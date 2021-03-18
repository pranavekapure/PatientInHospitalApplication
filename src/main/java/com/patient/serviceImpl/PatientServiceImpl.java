package com.patient.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.pojo.Patient;
import com.patient.service.PatientService;

@Service
public class PatientServiceImpl {

	@Autowired
	private PatientService patientService;

	public List<Patient> getPatientInfo() {
		List<Patient> patientList = patientService.findAll();
		return patientList;
	}

	public Patient saveOrUpdatePatientInfo(Patient patient) {
		Patient savedPatient = patientService.save(patient);
		return savedPatient;
	}

	public Optional<Patient> getPatient(Integer id) {
		Optional<Patient> patient = patientService.findById(id);
		return patient;

	}

	public String deletePatient(Integer id) {
		patientService.deleteById(id);
		return "deleted successfully";
	}

	public String deleteAllWithId(Integer hospitalId) {
		patientService.deleteAll(getPatientsWithId(hospitalId));
		return "deleted successfully";
	}

	public List<Patient> getPatientsWithId(Integer hospitalId) {
		return getPatientInfo().stream().filter(patient->patient.getHospitalId().equals(hospitalId)).collect(Collectors.toList());
	}
}
