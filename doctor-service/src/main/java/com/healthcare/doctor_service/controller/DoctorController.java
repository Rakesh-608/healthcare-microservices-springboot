package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.converters.DoctorDtoConverter;
import com.healthcare.doctor_service.dto.DoctorDto;
import com.healthcare.doctor_service.entity.Doctor;
import com.healthcare.doctor_service.service.DoctorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorDtoConverter doctorDtoConverter;

    public DoctorController(DoctorService doctorService, DoctorDtoConverter doctorDtoConverter) {
        this.doctorService = doctorService;
        this.doctorDtoConverter = doctorDtoConverter;
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorDto dto) {
        return ResponseEntity.ok(doctorService.createDoctor(dto));
    }

    @GetMapping("/getDoctorById/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable String id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorDtoConverter.convertToDto(doctor));
    }

    @PutMapping("/updateDoctor")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody DoctorDto dto) {
        return ResponseEntity.ok(doctorService.updateDoctor(dto));
    }

    @GetMapping("/getAllDoctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/getDoctorsByOpDays")
    public ResponseEntity<List<Doctor>> getDoctorsByOpDays(@RequestBody String[] days) {
        return  ResponseEntity.ok(doctorService.getDoctorsByOpDays(days));
    }

    @GetMapping("/getDoctorsBySpecialization/{specialization}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable String specialization) {
        return ResponseEntity
                .ok(doctorService.getDoctorsBySpecialization(specialization));
    }
}
