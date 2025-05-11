package com.code.api.services;

import com.code.api.entity.Appointment;
import java.util.List;

public interface IAppointmentService {

    List<Appointment> findAll();
    Appointment findById(int id);
    Appointment save(Appointment appointment);
    String deleteById(int id);
}
