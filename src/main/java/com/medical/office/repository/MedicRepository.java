package com.medical.office.repository;

import com.medical.office.entity.Medic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    List<Medic> findBySpecialty(String specialty);
}
