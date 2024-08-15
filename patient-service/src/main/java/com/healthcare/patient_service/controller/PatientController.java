package com.healthcare.patient_service.controller;

import com.healthcare.patient_service.Entity.Patient;
import com.healthcare.patient_service.Entity.PreExistingIllness;
import com.healthcare.patient_service.converters.PatientDtoConverter;
import com.healthcare.patient_service.dto.IllnessDTO;
import com.healthcare.patient_service.dto.PatientDTO;
import com.healthcare.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientDtoConverter patientDtoConverter;

    public PatientController(PatientService patientService, PatientDtoConverter patientDtoConverter) {
        this.patientService = patientService;
        this.patientDtoConverter = patientDtoConverter;
    }

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> add(@Valid @RequestBody PatientDTO dto){
        Patient patient = patientService.addPatient(dto);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/addIllness")
    public ResponseEntity<PreExistingIllness> addIllness(@RequestBody IllnessDTO dto){
        PreExistingIllness illness= patientService.addIllness(dto);
        return ResponseEntity.ok(illness);
    }

    @GetMapping("/getByPhoneNumber/{phno}")
    public ResponseEntity<Patient> getByPhoneNumber(@PathVariable String phno){
        Optional<Patient> patient= patientService.searchByPhoneNumber(phno);
        return ResponseEntity.ok(patient.get());
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<PatientDTO> getByEmail(@PathVariable String email){
        Optional<Patient> patient= patientService.searchByEmail(email);
        PatientDTO dto= patientDtoConverter.toDto(patient.get());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Optional<Patient>> updatePatient(@PathVariable int id, @RequestBody PatientDTO dto){
        Optional<Patient> patient= patientService.updatePatient(id,dto);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable int id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient with id: "+id+" is deleted");
    }

    @GetMapping("/getAllUniqueIllness")
    public ResponseEntity<Set<String>> getAllIllnessList(){
        return ResponseEntity.ok(patientService.uniqueIllnessNames());
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<Iterable<Patient>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable int id){
        PatientDTO patient= patientDtoConverter.toDto(patientService.getPatientById(id));
        return ResponseEntity.ok(patient);
    }

}
