package com.healthcare.patient_service.service;

import com.healthcare.patient_service.Entity.Patient;
import com.healthcare.patient_service.Entity.PreExistingIllness;
import com.healthcare.patient_service.dto.IllnessDTO;
import com.healthcare.patient_service.dto.PatientDTO;
import com.healthcare.patient_service.exceptions.DuplicatePatientException;
import com.healthcare.patient_service.exceptions.PatientNotFoundException;
import com.healthcare.patient_service.repository.IllnessRepository;
import com.healthcare.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final IllnessRepository illnessRepository;

    public PatientService(PatientRepository patientRepository, IllnessRepository illnessRepository) {
        this.patientRepository = patientRepository;
        this.illnessRepository = illnessRepository;
    }

    public PreExistingIllness addIllness(IllnessDTO dto){
        PreExistingIllness illness = new PreExistingIllness();
        illness.setIllnessName(dto.illnessName());
        return illnessRepository.save(illness);
    }


    public Patient addPatient(PatientDTO dto){
        Patient patient = new Patient();
        patient.setPatientName(dto.patientName());
        patient.setEmail(dto.email());
        patient.setPhoneNumber(dto.phoneNumber());
        patient.setAddress(dto.address());
        patient.setPreExistingIllness(dto.preExistingIllnessSet());
        return patientRepository.save(patient);
    }

    public Patient createPatient(PatientDTO dto){
        Patient patient = new Patient();
        patient.setPatientName(dto.patientName());
        patient.setEmail(dto.email());
        patient.setPhoneNumber(dto.phoneNumber());
        patient.setAddress(dto.address());
        patient.setPreExistingIllness(dto.preExistingIllnessSet());
        log.debug("Creating patient: {}", patient);
        patientRepository
                .findByEmailOrPhoneNumber(patient.getEmail(), patient.getPhoneNumber())
                .ifPresent(existingPatient -> {
                    log.error("Patient already exists with email: {} or phone: {}",
                            existingPatient.getEmail(), existingPatient.getPhoneNumber());
                    throw new DuplicatePatientException("Patient already exists with email: " +
                            existingPatient.getEmail() + " or phone: " + existingPatient.getPhoneNumber());
                });
        log.debug("Patient does not exist with email: {} or phone: {}", patient.getEmail(), patient.getPhoneNumber());
        return patientRepository.save(patient);
    }

    public Optional<Patient> searchByPhoneNumber(String phoneNumber){
        Optional<Patient> patient;
        patient = Optional.ofNullable(patientRepository
                .findByPhoneNumber(phoneNumber)
//                .ifPresent(p -> {
//                    log.error("No Patient exists with phone: {}", p.getPhoneNumber());
//                    throw new PatientNotFoundException("No Patient exists with the phoneNumber: " + p.getPhoneNumber());
//                }));

                .orElseThrow(()-> new PatientNotFoundException("No patient registered with the "+phoneNumber)));
        return patient;
    }

    public Optional<Patient> searchByEmail(String email){
        Optional<Patient> patient = Optional.ofNullable(patientRepository.findByEmail(email).orElseThrow(()-> new PatientNotFoundException("No patient registered with the "+email)));
        return patient;
    }

    public Optional<Patient> updatePatient(int id, PatientDTO dto){
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
            patient.get().setPatientName(dto.patientName());
            patient.get().setEmail(dto.email());
            patient.get().setPhoneNumber(dto.phoneNumber());
            patient.get().setAddress(dto.address());
            patient.get().setPreExistingIllness(dto.preExistingIllnessSet());
            return Optional.of(patientRepository.save(patient.get()));
        }else {
            throw new PatientNotFoundException("Patient not found with id: "+id);
        }
    }

    public void deletePatient(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }

    public Set<String> uniqueIllnessNames() {
        List<PreExistingIllness> illnessList = illnessRepository.findAll();
        Set<String> uniqueIllnessNames = illnessList.stream()
                .map(PreExistingIllness::getIllnessName) // Extract illness names
                .collect(Collectors.toSet());

        return uniqueIllnessNames;
    }

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }


    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));
    }
}
