package com.healthcare.patient_service.dto;

import com.healthcare.patient_service.Entity.PreExistingIllness;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record PatientDTO(
        int id,

        @NotEmpty(message = "Name not be empty")
        @Size(min = 3, max=50, message = "Name should have at least 3 characters")
        String patientName,

        @NotEmpty(message = "Email not be empty")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        String email,

        @NotEmpty(message = "Phone number not be empty")
        @Pattern(regexp = "^\\d{10}$")
        String phoneNumber,

        @NotEmpty(message = "Address not be empty")
        String address,

        Set<PreExistingIllness> preExistingIllnessSet

){
}
