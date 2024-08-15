package com.healthcare.appointment_service.feign_clients;

import com.healthcare.appointment_service.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "http://localhost:8100/api/v1/patients")
public interface PatientServiceClient {

    @GetMapping("/getPatientById/{id}")
    public PatientDto getPatientById(@PathVariable long id);

}
