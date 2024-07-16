package com.medical.office.controller;

import com.medical.office.entity.Medic;
import com.medical.office.service.MedicService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/medics")
public class MedicController {


    @Autowired
    private MedicService medicService;


    @GetMapping
    public List<Medic> getMedicAll(){
        return medicService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medic> getMedicById(@PathVariable Long id){
        Optional<Medic> medic = medicService.findById(id);
        return medic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medic> createMedic(@RequestBody Medic medic){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicService.save(medic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medic> updateMedic(@PathVariable Long id, @RequestBody Medic medicDetails){
        Optional<Medic> medicOptional = medicService.findById(id);
        if (medicOptional.isPresent()){
            Medic medic = medicOptional.get();
            medic.setName(medicDetails.getName());
            medic.setSpecialty(medicDetails.getSpecialty());
            return ResponseEntity.ok(medicService.save(medic));
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedic(@PathVariable Long id) {
        medicService.deleteId(id);
        return ResponseEntity.ok().build();
    }
}
