package com.medical.office.service;

import com.medical.office.entity.Medic;
import com.medical.office.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicService {
    @Autowired
    private MedicRepository medicRepository;

    public List<Medic> findAll(){
        return medicRepository.findAll();
    }

    public Optional<Medic> findById(Long id){
        return medicRepository.findById(id);
    }

    public Medic save(Medic medic){
        return medicRepository.save(medic);
    }

    public void deleteId(Long id){
        medicRepository.deleteById(id);
    }
}
