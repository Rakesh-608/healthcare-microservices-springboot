package com.healthcare.appointment_service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    @Field("appointment_date")
    private String appointmentDate;

    @Field("appointment_time")
    private String appointmentTime;

    @Field("patient_id")
    private long patientId;

    @Field("patient_name")
    private String patientName;

    @Field("patient_number")
    private String patientNumber;

    @Field("doctor_id")
    private String doctorId;

    @Field("doctor_name")
    private String doctorName;

    private String status;

    public Appointment(String appointmentDate, String appointmentTime, long l, String patientName, String patientNumber, String doctorId, String doctorName, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patientId = l;
        this.patientName = patientName;
        this.patientNumber = patientNumber;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.status = status;
    }
}
