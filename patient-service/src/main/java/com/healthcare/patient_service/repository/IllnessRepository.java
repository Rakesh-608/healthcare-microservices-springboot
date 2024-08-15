package com.healthcare.patient_service.repository;

import com.healthcare.patient_service.Entity.PreExistingIllness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IllnessRepository extends JpaRepository<PreExistingIllness, Integer> {
   Optional<PreExistingIllness> findByIllnessName(String illnessName);
}
