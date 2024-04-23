package com.ikechukwu.ihospitality.security;

import com.ikechukwu.ihospitality.exception.DataNotFoundException;
import com.ikechukwu.ihospitality.model.Staff;
import com.ikechukwu.ihospitality.repository.StaffRepository;
import com.ikechukwu.ihospitality.util.LibraryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UuidProvider {

    @Autowired
    private StaffRepository staffRepository;

    public static String generateToken2 () {
        return LibraryUtils.generateUUID();
    }

    public Staff getStaffUser(String uuid) {
        return staffRepository.findByUuid(uuid).orElseThrow(() ->
                new DataNotFoundException("404", "Staff with uuid: " + uuid + ", not found."));
    }

    public boolean validateStaff(String uuid) {
        try {
            return staffRepository.existsByUuid(uuid);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

}
