package com.healthcare.patient_service.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_table", uniqueConstraints = {@UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "phoneNumber")})
//@EntityListeners(AuditingEntityListener.class)
//@NamedQueries({
//        @NamedQuery(name = "Patient.findByEmail",
//                query = "select p from patient_table p where p.email = :email"),
//        @NamedQuery(name = "Patient.findByPhone",
//                query = "select p from patient_table p where p.phoneNumber = :phoneNumber"),
//        @NamedQuery(name = "Patient.findByEmailOrPhone",
//                query = "select p from patient_table p where p.email = :email OR p.phoneNumber = :phoneNumber"),
//})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String patientName;
    private String email;
    private String phoneNumber;
    private String address;



    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "patient_pre_existing_illness",
            joinColumns = @JoinColumn(name = "patient_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "pre_existing_illness_id", nullable = true))
    private Set<PreExistingIllness> preExistingIllness;
}
