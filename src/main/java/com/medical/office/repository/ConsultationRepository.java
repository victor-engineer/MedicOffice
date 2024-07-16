package com.medical.office.repository;

import com.medical.office.entity.Consultation;
import com.medical.office.entity.Medic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByMedicAndDataHoraBetween(Medic medic, LocalDateTime start, LocalDateTime end);
}
