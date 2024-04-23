package com.ikechukwu.ihospitality.controller;

import com.ikechukwu.ihospitality.dto.StaffDto;
import com.ikechukwu.ihospitality.response.APIResponse;
import com.ikechukwu.ihospitality.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/create")
    ResponseEntity<APIResponse<?>> createStaff(@RequestBody @Validated StaffDto staffDto) {
        return staffService.addStaff(staffDto);
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<APIResponse<?>> updateStaff(@PathVariable String uuid, @RequestBody @Valid StaffDto staffDto) {
        return staffService.updateStaff(uuid, staffDto);
    }

}
