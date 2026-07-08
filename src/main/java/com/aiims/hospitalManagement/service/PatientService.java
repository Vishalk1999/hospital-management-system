package com.aiims.hospitalManagement.service;

import com.aiims.hospitalManagement.entity.Patient;
import com.aiims.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
 private final PatientRepository patientRepository;

                        /* when this method is complete without error then commit else all
                        operation are rollback,more powerfull annotation
                                directly contact with database. @Transactional */
 @Transactional
    public Patient getPatientById(Long id) {

        Patient p1 = patientRepository.findById(id).orElseThrow();
        Patient p2 = patientRepository.findById(id).orElseThrow();

        System.out.println(p1==p2);  //refer same location because of persistance Contact through

     p1.setName("Ajay");   //persistance State
        return p1;
    }
}
