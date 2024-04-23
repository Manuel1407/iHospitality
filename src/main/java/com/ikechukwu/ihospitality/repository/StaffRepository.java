package com.ikechukwu.ihospitality.repository;

import com.ikechukwu.ihospitality.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByUuid(String uuid);

    boolean existsByUuid(String staffUuid);

}
