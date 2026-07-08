package com.aiims.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false,length = 100,unique = true)
    private String email;


    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments = new HashSet<>();
}
