package com.healthcare.doctor_service.repository;

import com.healthcare.doctor_service.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findAllByOpDaysContains(String[] days);

    List<Doctor> findAllBySpecializationContains(String specialization);
}
