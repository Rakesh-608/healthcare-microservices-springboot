package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.converters.DoctorDtoConverter;
import com.healthcare.doctor_service.dto.DoctorDto;
import com.healthcare.doctor_service.entity.Doctor;
import com.healthcare.doctor_service.exceptions.DoctorNotFoundException;
import com.healthcare.doctor_service.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorDtoConverter doctorDtoConverter;

    public DoctorService(DoctorRepository doctorRepository, DoctorDtoConverter doctorDtoConverter) {
        this.doctorRepository = doctorRepository;
        this.doctorDtoConverter = doctorDtoConverter;
    }

    public Doctor createDoctor(DoctorDto dto) {
        return doctorRepository.save(doctorDtoConverter.convertToEntity(dto));
    }

    public Doctor getDoctorById(String id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if(doctor.isPresent()) {
            return doctor.get();
        }else{
            throw new DoctorNotFoundException("Doctor not found with id: " + id);
        }
    }

    public Doctor updateDoctor(DoctorDto dto) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(dto.id());
        if(existingDoctor.isPresent()) {
            doctorDtoConverter.convertToEntity(dto);
            return doctorRepository.save(existingDoctor.get());
        }else{
            throw new DoctorNotFoundException("Doctor not found with id: " + dto.id());
        }
    }


    public List<Doctor> getDoctorsByOpDays(String[] days){
        return doctorRepository.findAllByOpDaysContains(days);
    }

    public List<Doctor> getAllDoctors(){
        return List.copyOf(doctorRepository.findAll());
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return List.copyOf(doctorRepository.findAllBySpecializationContains(specialization));
    }
}
