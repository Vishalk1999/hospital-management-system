package com.aiims.hospitalManagement.repository;

import com.aiims.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.aiims.hospitalManagement.entity.Patient;
import com.aiims.hospitalManagement.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String name);
    List<Patient> findByBirthDateOrEmail(LocalDate birthDate , String email);
    List<Patient> findByBirthDateBetween(LocalDate startDate,LocalDate endDate);
    List<Patient> findByNameContaining(String query);
    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p from Patient p where p.bloodGroup = ?1")     //JPQL lang Hibernate Convert JPQL to SQL
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    //@Query("select p.bloodGroup , count(p) from Patient p group by p.bloodGroup")         //GroupBy
   // List<Object[]> countEachBloodGroupType(); //want all patient bloodgroup
    @Query("select new com.aiims.hospitalManagement.dto.BloodGroupCountResponseEntity"
            +"(p.bloodGroup," + " count(p)) from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();


    //Native Query
   // @Query(value="select * from patient ",nativeQuery = true)
    //List<Patient> findAllPatients();

    //Pageable used for retrieve large datasets in smaller chunks (pages) instead of loading all records at once.
    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    //UPDATE Query
    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name=:name where p.id=:id")
    int updateByNameWithId(@Param("name") String name,@Param("id") Long id);

}
