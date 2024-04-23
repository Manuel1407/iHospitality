package com.ikechukwu.ihospitality.service.impl;

import com.ikechukwu.ihospitality.exception.DataNotFoundException;
import com.ikechukwu.ihospitality.model.Patient;
import com.ikechukwu.ihospitality.repository.PatientRepository;
import com.ikechukwu.ihospitality.response.APIResponse;
import com.ikechukwu.ihospitality.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CSVService csvService;

    public ResponseEntity<APIResponse<?>> findPatients(int age) {
        List<Patient> patients = patientRepository.findAllByAgeIsLessThanEqual(age);
        if (patients.isEmpty()) {
            APIResponse<List<Patient>> response = new APIResponse<>("404", "No patient aged up to " + age, null);
            return ResponseEntity.ok(response);
        }
        APIResponse<List<Patient>> response = new APIResponse<>("200", "Successful", patients);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<InputStreamResource> downloadCsv(String name) {
        Patient patient = patientRepository.findByName(name).orElseThrow(() ->
                new DataNotFoundException("404", "Patient with name: " + name + ", not found."));
        ByteArrayInputStream csvStream = csvService.generatePatientCsv(patient);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="+ name +".csv");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(csvStream));
    }

    @Transactional
    public ResponseEntity<APIResponse<?>> deletePatientsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Patient> patientsToDelete = patientRepository.findByLastVisitDateBetween(startDate, endDate);
        if (patientsToDelete.isEmpty()) {
            APIResponse<List<Patient>> response = new APIResponse<>("404", "No patient found within this date range.", null);
            return ResponseEntity.ok(response);
        }
        for (Patient patient : patientsToDelete) {
            patientRepository.deleteById(patient.getId());
        }
        APIResponse<String> response = new APIResponse<>("200", "Successful", "Patients data deleted successfully.");
        return ResponseEntity.ok(response);
    }

}
