package com.code.api.controller;

import com.code.api.entity.Appointment;
import com.code.api.entity.User;
import com.code.api.services.AppointmentService;
import com.code.api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.findAll();
    }
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        Appointment appointment = appointmentService.findById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/book/{id}")
    public ResponseEntity<Appointment> createAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        User user = userService.findById(id); // Make sure userService is @Autowired
        appointment.setUser(user);
        Appointment savedAppointment = appointmentService.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
        
    }




    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmentService.save(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id) {
        String response = appointmentService.deleteById(id);
        return ResponseEntity.ok(response);
    }
}
