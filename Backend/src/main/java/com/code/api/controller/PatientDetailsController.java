package com.code.api.controller;

import com.code.api.entity.PatientDetails;
import com.code.api.services.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient-details")
public class PatientDetailsController {

    @Autowired
    private PatientDetailsService patientDetailsService;

    @GetMapping
    public List<PatientDetails> getAllPatientDetails() {
        return patientDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetails> getPatientDetailsById(@PathVariable int id) {
        PatientDetails patientDetails = patientDetailsService.findById(id);
        return ResponseEntity.ok(patientDetails);
    }

    @PostMapping
    public ResponseEntity<PatientDetails> createPatientDetails(@RequestBody PatientDetails patientDetails) {
        PatientDetails savedPatientDetails = patientDetailsService.save(patientDetails);
        return ResponseEntity.status(201).body(savedPatientDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDetails> updatePatientDetails(@PathVariable int id, @RequestBody PatientDetails patientDetails) {
        PatientDetails updatedPatientDetails = patientDetailsService.save(patientDetails);
        return ResponseEntity.ok(updatedPatientDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatientDetails(@PathVariable int id) {
        String response = patientDetailsService.deleteById(id);
        return ResponseEntity.ok(response);
    }
}
