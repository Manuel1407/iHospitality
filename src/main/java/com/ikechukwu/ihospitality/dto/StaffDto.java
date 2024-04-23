package com.ikechukwu.ihospitality.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffDto {

    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 5, max = 100, message = "Full name must be between 5 and 100 characters")
    private String name;

    private String uuid;

    private LocalDate registrationDate;

}
