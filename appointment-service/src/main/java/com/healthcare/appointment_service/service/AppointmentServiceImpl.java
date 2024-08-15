package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.dto.DoctorDto;
import com.healthcare.appointment_service.dto.PatientDto;
import com.healthcare.appointment_service.entity.Appointment;
import com.healthcare.appointment_service.feign_clients.DoctorServiceClient;
import com.healthcare.appointment_service.feign_clients.PatientServiceClient;
import com.healthcare.appointment_service.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorServiceClient doctorServiceClient;
    private final PatientServiceClient patientServiceClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorServiceClient doctorServiceClient, PatientServiceClient patientServiceClient) {
        this.appointmentRepository = appointmentRepository;
        this.doctorServiceClient = doctorServiceClient;
        this.patientServiceClient = patientServiceClient;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> findAllByPatientId(String patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Appointment> findAllByDoctorId(String doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId) {
        return appointmentRepository.findAllByPatientIdAndDoctorId(patientId, doctorId);
    }

    @Override
    public PatientDto getPatientById(String patientId) {
        return patientServiceClient.getPatientById(Long.parseLong(patientId));
    }

    @Override
    public DoctorDto getDoctorById(String doctorId) {
        return doctorServiceClient.getDoctorById(doctorId);
    }
}
