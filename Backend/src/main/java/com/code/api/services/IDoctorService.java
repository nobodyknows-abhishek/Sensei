package com.code.api.services;

import com.code.api.entity.Doctor;
import java.util.List;

public interface IDoctorService {

    List<Doctor> findAll();
    Doctor findById(int id);
    Doctor save(Doctor doctor);
    String deleteById(int id);
}
