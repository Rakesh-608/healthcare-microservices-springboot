package com.healthcare.doctor_service.converters;

import com.healthcare.doctor_service.dto.DoctorDto;
import com.healthcare.doctor_service.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorDtoConverter {
    public DoctorDto convertToDto(Doctor doctor) {
        if(doctor.getId() == null)
            throw new RuntimeException("Doctor id cannot be null");
        DoctorDto dto = new DoctorDto(doctor.getId(), doctor.getName(), doctor.getEmail(),doctor.getPhone(), doctor.getSpecialization(),doctor.getOpDays());
        return dto;
    }

    public Doctor convertToEntity(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setId(dto.id());
        doctor.setName(dto.name());
        doctor.setEmail(dto.email());
        doctor.setPhone(dto.phone());
        doctor.setSpecialization(dto.specialization());
        doctor.setOpDays(dto.opDays());
        return doctor;
    }
}
