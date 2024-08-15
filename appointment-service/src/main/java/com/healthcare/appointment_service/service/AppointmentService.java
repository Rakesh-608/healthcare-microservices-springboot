package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.dto.DoctorDto;
import com.healthcare.appointment_service.dto.PatientDto;
import com.healthcare.appointment_service.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);

    public Appointment getAppointmentById(String id);

    List<Appointment> findAllByPatientId(String patientId);

    List<Appointment> findAllByDoctorId(String doctorId);

    List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId);

    PatientDto getPatientById(String patientId);

    DoctorDto getDoctorById(String doctorId);
}

// mvn clean install package -f /author-service/pom.xml
// java -jar