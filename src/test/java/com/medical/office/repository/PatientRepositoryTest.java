package com.medical.office.repository;

import com.medical.office.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PatientRepositoryTest {


    @Autowired
    private PatientRepository patientRepository;

    void testFindById(){
        Patient patient = new Patient();
        patient.setName("Natasha Romanoff");
        patient = patientRepository.save(patient);


        Optional<Patient> foundPatient = patientRepository.findById(patient.getId());
        assertTrue(foundPatient.isPresent());
        assertEquals("Natasha Romanoff", foundPatient.get().getName());


        Patient savedPatient = patientRepository.save(patient);
        assertNotNull(savedPatient.getId());
        assertEquals("Natasha Romanoff", savedPatient.getName());
    }
}
