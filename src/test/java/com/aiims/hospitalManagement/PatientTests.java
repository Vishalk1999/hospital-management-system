package com.aiims.hospitalManagement;

import com.aiims.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.aiims.hospitalManagement.entity.Patient;
import com.aiims.hospitalManagement.repository.PatientRepository;
import com.aiims.hospitalManagement.service.PatientService;
import com.aiims.hospitalManagement.type.BloodGroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {    //Test data are not save in database they rollback operation perform.
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }

    @Test
    public void testTranctionalMethod() {

        /************************   All Get Query   *************************************************/

//        Patient patient =patientService.getPatientById(2L);
//        Patient patient =patientService.getPatientById(5L); //NoSuchElementException: No value present
//        Patient patient =patientRepository.findByName("Kabir Singh");
//        List<Patient> patientList=patientRepository.findByBirthDateOrEmail(LocalDate.of(1999,12,01),
//               "aarav.sharma@example.com");
//         List<Patient> patientList =patientRepository.findByNameContaining("di");
//         List<Patient> patientList =patientRepository.findByNameContainingOrderByIdDesc("di");
//         List<Patient> patientList =patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);  //take type as BloodGroupType
 //        List<Patient> patientList =patientRepository.findByBornAfterDate(LocalDate.of(1970,12,12));
  /*         List<Patient> patientList = patientRepository.findAllPatients();
                for (Patient patient : patientList) {
              System.out.println(patient);
              }

   */
  /*  Array of Array
    List<Object[]> bloodGroupList =patientRepository.countEachBloodGroupType();
             for(Object[] objects:bloodGroupList){
                System.out.println(objects[0]+" "+objects[1]);
           }
   */
        //Projection
            //Using Reference for JSON format using creating constructor

         /*  List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
        for (BloodGroupCountResponseEntity bloodGroupCountResponseEntity : bloodGroupList) {
            System.out.println(bloodGroupCountResponseEntity);
         */
          // Pagination
            Page<Patient> patientList = patientRepository
                    .findAllPatients(PageRequest.of(0, 2,Sort.by("name")));
            for(Patient patient: patientList) {
                System.out.println(patient);

            /************************   All Upddate Query   *************************************************/
/*
            int rowUpdated = patientRepository.updateByNameWithId("vishal kale", 1L);
            System.out.println(rowUpdated);

 */
        }
    }
}