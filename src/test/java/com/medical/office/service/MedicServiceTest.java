package com.medical.office.service;

import com.medical.office.entity.Medic;
import com.medical.office.repository.MedicRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MedicServiceTest {

    @InjectMocks
    private MedicService medicService;

    @Mock
    private MedicRepository medicRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll(){
        Medic medic1 = new Medic();
        medic1.setName("Dr.Luan Barbosa");
        Medic medic2 = new Medic();
        medic2.setName("Dr.Victor de Lima");

        List<Medic> medicList = Arrays.asList(medic1, medic2);
        given(medicRepository.findAll()).willReturn(medicList);

        List<Medic> result = medicService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Dr.Luan Barbosa", result.get(0).getName());
        assertEquals("Dr.Victor de Lima", result.get(1).getName());

    }

    @Test
    void testFindById() {

        Medic medic = new Medic();
        medic.setName("Dr. Luan Barbosa");

        given(medicRepository.findById(anyLong())).willReturn(Optional.of(medic));


        Optional<Medic> result = medicService.findById(1L);


        assertTrue(result.isPresent());
        assertEquals("Dr. Luan Barbosa", result.get().getName());
    }

    @Test
    void testSave() {
        // Dado
        Medic medic = new Medic();
        medic.setName("Dr. Luan Barbosa");

        given(medicRepository.save(any(Medic.class))).willReturn(medic);

        Medic result = medicService.save(medic);

        assertNotNull(result);
        assertEquals("Dr. Luan Barbosa", result.getName());
    }

    @Test
    void testDeleteById() {

        medicService.deleteId(1L);

        verify(medicRepository, times(1)).deleteById(1L);
    }
}
