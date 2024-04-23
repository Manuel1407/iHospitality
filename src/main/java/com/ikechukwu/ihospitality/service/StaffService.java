package com.ikechukwu.ihospitality.service;

import com.ikechukwu.ihospitality.dto.StaffDto;
import com.ikechukwu.ihospitality.model.Staff;
import com.ikechukwu.ihospitality.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface StaffService {


    ResponseEntity<APIResponse<?>> addStaff(StaffDto name);
    ResponseEntity<APIResponse<?>> updateStaff(String uuid, StaffDto staffDto);

    boolean existsByUuid(String uuid);
}
