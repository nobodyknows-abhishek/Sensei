package com.code.api.controller;

import com.code.api.entity.Doctor;
import com.code.api.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.findById(id);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.save(doctor);
        return ResponseEntity.status(201).body(savedDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
         // ensure the ID is set
        Doctor updatedDoctor = doctorService.save(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) {
        try {
            Doctor doctor = doctorService.findById(id);
            System.out.println("Deleting doctor: " + doctor.getName());
            
            doctorService.deleteById(id);
            return ResponseEntity.ok("Doctor deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Doctor not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting doctor: " + e.getMessage());
        }
    }

}
