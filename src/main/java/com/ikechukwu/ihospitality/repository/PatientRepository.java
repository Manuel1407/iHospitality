package com.ikechukwu.ihospitality.repository;

import com.ikechukwu.ihospitality.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByAgeIsLessThanEqual(int age);

    List<Patient> findByLastVisitDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<Patient> findByName(String name);

}