package com.ikechukwu.ihospitality.service.impl;

import com.ikechukwu.ihospitality.model.Patient;
import com.ikechukwu.ihospitality.repository.PatientRepository;
import com.ikechukwu.ihospitality.response.APIResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private CSVService csvService;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindPatientsSuccess() {
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient("John", 25, LocalDate.parse("2023-01-06")));
        mockPatients.add(new Patient("Jane", 30, LocalDate.parse("2023-01-08")));
        when(patientRepository.findAllByAgeIsLessThanEqual(anyInt())).thenReturn(mockPatients);
        ResponseEntity<APIResponse<?>> responseEntity = patientService.findPatients(30);
        assertEquals("200", responseEntity.getBody().getStatusCode());
        assertEquals("Successful", responseEntity.getBody().getMessage());
        assertEquals(mockPatients, responseEntity.getBody().getData());
    }

    @Test
    public void testFindPatientsNoPatients() {
        List<Patient> mockPatients = new ArrayList<>();
        when(patientRepository.findAllByAgeIsLessThanEqual(anyInt())).thenReturn(mockPatients);
        ResponseEntity<APIResponse<?>> responseEntity = patientService.findPatients(30);
        assertEquals("404", responseEntity.getBody().getStatusCode());
        assertEquals("No patient aged up to 30", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
    }
}