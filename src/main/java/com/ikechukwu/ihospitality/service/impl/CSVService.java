package com.ikechukwu.ihospitality.service.impl;

import com.ikechukwu.ihospitality.model.Patient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CSVService {

    public ByteArrayInputStream generatePatientCsv(Patient patient) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(
                     new PrintWriter(out),
                     CSVFormat.DEFAULT.withHeader("Name", "Age", "Last Visit Date"))) {
            csvPrinter.printRecord(patient.getName(), patient.getAge(), patient.getLastVisitDate());
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException ex) {
            throw new RuntimeException("Failed to generate CSV file", ex);
        }
    }

}
