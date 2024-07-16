package com.medical.office.controller;

import com.medical.office.entity.Patient;
import com.medical.office.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatient(){
        return patientService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);
        return patient.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    @PutMapping("{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails){
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isPresent()){
            Patient patient = patientOptional.get();
            patient.setName(patientDetails.getName());
            patient.setCpf(patientDetails.getCpf());
            patient.setTelefone(patientDetails.getTelefone());
            return ResponseEntity.ok(patientService.save(patient));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
