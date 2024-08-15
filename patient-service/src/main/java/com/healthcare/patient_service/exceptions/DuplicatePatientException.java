package com.healthcare.patient_service.exceptions;

public class DuplicatePatientException extends RuntimeException{
    public DuplicatePatientException(String message){
        super(message);
    }
}
