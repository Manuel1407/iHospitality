package com.ikechukwu.ihospitality.service.impl;

import com.ikechukwu.ihospitality.dto.StaffDto;
import com.ikechukwu.ihospitality.exception.DataNotFoundException;
import com.ikechukwu.ihospitality.model.Staff;
import com.ikechukwu.ihospitality.repository.StaffRepository;
import com.ikechukwu.ihospitality.response.APIResponse;
import com.ikechukwu.ihospitality.security.UuidProvider;
import com.ikechukwu.ihospitality.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public ResponseEntity<APIResponse<?>> addStaff(StaffDto staffInfo) {
        Staff staff = Staff.builder()
                .name(staffInfo.getName())
                .uuid(UuidProvider.generateToken2())
                .registrationDate(LocalDate.now())
                .build();
        try {
            staffRepository.save(staff);
            APIResponse<Staff> response = new APIResponse<>("200", "Staff created successfully", staff);
            log.info("Staff created successfully: {}", staff);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            log.error("An unexpected error occurred while creating staff ", ex);
            APIResponse<String> errorResponse = new APIResponse<>("500", "An error occurred, please try again later.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> updateStaff(String uuid, StaffDto staffDto) {
        Optional<Staff> existingStaff = staffRepository.findByUuid(uuid);
        if (existingStaff.isPresent()) {
            Staff staff = existingStaff.get();
            staff.setName(staffDto.getName());
            try {
                staffRepository.save(staff);
                log.info("Staff updated successfully: {}", staff);
                APIResponse<Staff> response = new APIResponse<>("200", "Staff updated successfully", staff);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                log.error("An unexpected error occurred while updating staff ", e);
                throw new RuntimeException("Failed to update staff", e);
            }

        } else {
            throw new DataNotFoundException("404", "Staff with UUID " + uuid + " not found");
        }
    }

    public boolean existsByUuid(String uuid) {
        return true;
    }

    public boolean isStaffValid(String staffUuid) {
        return staffRepository.existsByUuid(staffUuid);
    }
}
