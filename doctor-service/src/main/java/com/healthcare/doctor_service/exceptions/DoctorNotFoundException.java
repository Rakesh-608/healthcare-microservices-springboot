package com.healthcare.doctor_service.exceptions;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String s) {
        super(s);
    }
}
