package com.healthcare.doctor_service.dto;

public record DoctorDto(
        String id,
        String name,
        String email,
        String phone,
        String[] specialization,
        String[] opDays
) {
}
