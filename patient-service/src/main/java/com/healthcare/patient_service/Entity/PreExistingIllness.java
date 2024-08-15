package com.healthcare.patient_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.CascadeType;
//import javax.persistence.FetchType;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PreExistingIllness")
public class PreExistingIllness {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String illnessName;

    @JsonIgnore
    @ManyToMany(mappedBy = "preExistingIllness")
    private Set<Patient> patients;

    public PreExistingIllness(PreExistingIllness illness) {
        this.illnessName = illness.getIllnessName();
    }
}

//{
//        "patientName":"Venu",
//        "email":"venu@gmail.com",
//        "phoneNumber":"1234567890",
//        "address":"Hyderabad",
//        "dob":"15:10:2002",
//        "preExistingIllnessList":[
//        "cold",
//        "fever"
//        ]
//
//        }
