package com.medical.office.service;

import com.medical.office.entity.Consultation;
import com.medical.office.entity.Medic;
import com.medical.office.repository.ConsultationRepository;
import com.medical.office.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private MedicRepository medicRepository;

    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    public Optional<Consultation> findById(Long id){
        return consultationRepository.findById(id);
    }

    public Consultation save(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public void deleteById(Long id){
        consultationRepository.deleteById(id);
    }

    public List<Consultation> findByMedicoIdAndDataHoraBetween(Long medicId, LocalDateTime start, LocalDateTime end) {
        // Busca o objeto Medic pelo ID
        Medic medic = medicRepository.findById(medicId)
                .orElseThrow(() -> new IllegalArgumentException("Medic not found with id: " + medicId));

        // Usa o objeto Medic para chamar o método no repositório
        return consultationRepository.findByMedicAndDataHoraBetween(medic, start, end);
    }
}
