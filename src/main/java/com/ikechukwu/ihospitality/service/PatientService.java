package com.ikechukwu.ihospitality.service;

import com.ikechukwu.ihospitality.dto.PatientDto;
import com.ikechukwu.ihospitality.response.APIResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface PatientService {

    ResponseEntity<APIResponse<?>> findPatients(int age);

    ResponseEntity<InputStreamResource> downloadCsv(String name);

    ResponseEntity<APIResponse<?>> deletePatientsByDateRange(LocalDate startDate, LocalDate endDate);
}
