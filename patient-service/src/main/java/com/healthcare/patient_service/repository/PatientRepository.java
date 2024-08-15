package com.healthcare.patient_service.repository;

import com.healthcare.patient_service.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    // findByPropertyName

    // select * from patients where email = 'email'
    // @Query("select p from Patient p where p.email = :email")
    Optional<Patient> findByEmail(String email);

    // select * from patients where phone = 'phone'
    // @Query("select p from Patient p where p.phone = :phone")
    Optional<Patient> findByPhoneNumber(String phone);

    // select * from patients where phone = 'phone' OR email = 'email'
    // @Query("select p from Patient p where p.phone = :phone OR p.email = :email")
    Optional<Patient> findByEmailOrPhoneNumber(String email, String phoneNumber);


    // Find the Illnesses of a Patient
    // select * from patient_illness where patient_id = 'patientId'
//    @Query("select p.illnesses from Patient p where p.id = :patientId")
//    Optional<Set<Patient>> findIllnessesOfPatient(@Param("patientId") long pid);
}
