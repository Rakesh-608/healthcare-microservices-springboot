package com.healthcare.patient_service.converters;

import com.healthcare.patient_service.Entity.Patient;
import com.healthcare.patient_service.Entity.PreExistingIllness;
import com.healthcare.patient_service.dto.PatientDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientDtoConverter {
    public PatientDTO toDto(Patient patient) {
        if (patient.getPreExistingIllness() == null) {
            return new PatientDTO(patient.getId(), patient.getPatientName(),
                    patient.getEmail(), patient.getPhoneNumber(), patient.getAddress(),
                    null);
        }
        PatientDTO patientDTO = new PatientDTO(patient.getId(), patient.getPatientName(),
                patient.getEmail(), patient.getPhoneNumber(), patient.getAddress(),
                patient.getPreExistingIllness());
//                            .stream().map(PreExistingIllness::getIllnessName).collect(Collectors.toSet()));
        return patientDTO;
    }

    public Patient toEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setPatientName(dto.patientName());
        patient.setEmail(dto.email());
        patient.setPhoneNumber(dto.phoneNumber());
        patient.setAddress(dto.address());
        if (!dto.preExistingIllnessSet().isEmpty()) {
            patient.setPreExistingIllness(dto.preExistingIllnessSet().stream().map(PreExistingIllness::new).collect(Collectors.toSet()));
        }
        return patient;
    }
}
