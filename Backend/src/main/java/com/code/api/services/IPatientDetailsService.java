package com.code.api.services;

import com.code.api.entity.PatientDetails;
import java.util.List;

public interface IPatientDetailsService {

    List<PatientDetails> findAll();
    PatientDetails findById(int id);
    PatientDetails save(PatientDetails patientDetails);
    String deleteById(int id);
}
