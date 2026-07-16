package com.aiims.hospitalManagement.service;

import com.aiims.hospitalManagement.entity.Appointment;
import com.aiims.hospitalManagement.entity.Doctor;
import com.aiims.hospitalManagement.entity.Patient;
import com.aiims.hospitalManagement.repository.AppointmentRepository;
import com.aiims.hospitalManagement.repository.DoctorRepository;
import com.aiims.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {


    private final  AppointmentRepository appointmentRepository;                  //taking repository
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment,Long doctorId,Long patientId){     //create method

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();   //getting Doctor ,Patient
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have ");

                  appointment.setPatient(patient);   //set/update  Appointment to doctor and patient
                  appointment.setDoctor(doctor) ;

                  patient.getAppointments().add(appointment);  //to maintain consistency

             return  appointmentRepository.save(appointment);   //save appointment appointment for the patient/doctor

    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId){  //search appointment and doctor
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow();    //find appointment
        Doctor doctor =doctorRepository.findById(doctorId).orElseThrow();                          //find doctor available

        appointment.setDoctor(doctor);         //this will automatically call the update,because it is dirty now

        doctor.getAppointments().add(appointment);   //just for bidirectional consistency
        return appointment;
    }


    }

