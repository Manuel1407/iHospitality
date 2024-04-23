package com.ikechukwu.ihospitality.controller;

import com.ikechukwu.ihospitality.response.APIResponse;
import com.ikechukwu.ihospitality.service.PatientService;
import com.ikechukwu.ihospitality.service.impl.CSVService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private CSVService csvService;

    @GetMapping("/{age}")
    public ResponseEntity<APIResponse<?>> findPatientsByAge(@PathVariable int age) {
        return patientService.findPatients(age);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<APIResponse<?>> deletePatientsByDateRange(@RequestParam LocalDate startDate,
                                          @RequestParam LocalDate endDate) {
        return patientService.deletePatientsByDateRange(startDate, endDate);
    }

    @GetMapping("/data/download")
    public ResponseEntity<InputStreamResource> downloadCsv(
            @RequestParam @NotEmpty(message = "Name cannot be empty")
            @NotNull(message = "Name cannot be null") String name) {
        return patientService.downloadCsv(name);
    }

}
