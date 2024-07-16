package com.medical.office.service;

import com.medical.office.entity.Patient;
import com.medical.office.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Natasha Romanoff");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientService.findById(1L);
        assertTrue(foundPatient.isPresent());
        assertEquals("Natasha Romanoff", foundPatient.get().getName());
    }
}
