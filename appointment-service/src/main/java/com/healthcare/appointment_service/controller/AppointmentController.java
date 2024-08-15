package com.healthcare.appointment_service.controller;

import com.healthcare.appointment_service.dto.AppointmentDto;
import com.healthcare.appointment_service.dto.DoctorDto;
import com.healthcare.appointment_service.dto.PatientDto;
import com.healthcare.appointment_service.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto dto) {
        PatientDto patientDto = appointmentService.getPatientById(dto.patientId());
        if (patientDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        DoctorDto doctorDto = appointmentService.getDoctorById(dto.doctorId());
        if (doctorDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        var appointment = dto.toAppointment();
        appointment.setPatientName(patientDto.fullName());
        appointment.setPatientNumber(patientDto.phone());
        appointment.setDoctorName(doctorDto.fullName());
        var response = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(AppointmentDto.fromAppointment(response));
    }
}
