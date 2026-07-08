package com.aiims.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String reason;

    @Column(nullable = false)
    private LocalDate appointmentTime;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false) //Patient is required  and not nullable and @JoinColumn create Column Joi Column
    private Patient patient;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
