package com.medical.office.repository;

import com.medical.office.entity.Medic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    // faz com que o banco de dados seja limpo antes de cada teste
    @BeforeEach
    public void setUp(){
        medicRepository.deleteAll();
    }


    @Test
    void testFindById() {
        Medic medic = new Medic();
        medic.setName("Dr.Luan Barbosa");
        medic.setSpecialty("Cardiologia");
        medic = medicRepository.save(medic);

        Optional<Medic> foundMedic = medicRepository.findById(medic.getId());
        assertTrue(foundMedic.isPresent());
        assertEquals("Dr.Luan Barbosa", foundMedic.get().getName());
    }

    @Test
    void testSave() {
        Medic medic = new Medic();
        medic.setName("Dr.Victor Lima");
        medic.setSpecialty("Pediatria");

        Medic savedMedic = medicRepository.save(medic);
        assertNotNull(savedMedic.getId());
        assertEquals("Dr.Victor Lima", savedMedic.getName());
    }

    @Test
    void testFindBySpecialty() {
        Medic medic1 = new Medic();
        medic1.setName("Dr.Luan Barbosa");
        medic1.setSpecialty("Cardiologista");
        medicRepository.save(medic1);

        Medic medic2 = new Medic();
        medic2.setName("Dr.Victor Lima");
        medic2.setSpecialty("Pediatria");
        medicRepository.save(medic2);

        List<Medic> cardiologists = medicRepository.findBySpecialty("Cardiologista");
        assertNotNull(cardiologists);
        assertEquals(1, cardiologists.size());
        assertTrue(cardiologists.stream().anyMatch(m -> m.getName().equals("Dr.Luan Barbosa")));

        List<Medic> pediatricians = medicRepository.findBySpecialty("Pediatria");
        assertNotNull(pediatricians);
        assertEquals(1, pediatricians.size());
        assertTrue(pediatricians.stream().anyMatch(m -> m.getName().equals("Dr.Victor Lima")));
    }
}
