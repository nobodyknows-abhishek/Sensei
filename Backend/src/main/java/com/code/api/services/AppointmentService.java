package com.code.api.services;

import com.code.api.entity.Appointment;
import com.code.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepo.findAll();
    }

    @Override
    public Appointment findById(int id) {
        Optional<Appointment> optional = appointmentRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    @Override
    public String deleteById(int id) {
        appointmentRepo.deleteById(id);
        return "Appointment deleted successfully";
    }
}
