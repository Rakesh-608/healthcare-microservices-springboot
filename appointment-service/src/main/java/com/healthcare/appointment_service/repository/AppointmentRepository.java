package com.healthcare.appointment_service.repository;

import com.healthcare.appointment_service.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findAllByPatientId(String patientId);

    List<Appointment> findAllByDoctorId(String doctorId);

    List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId);
}
