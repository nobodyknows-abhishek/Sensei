package com.code.api.services;

import com.code.api.entity.PatientDetails;
import com.code.api.repository.PatientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientDetailsService implements IPatientDetailsService {

    @Autowired
    private PatientDetailsRepository patientDetailsRepo;

    @Override
    public List<PatientDetails> findAll() {
        return patientDetailsRepo.findAll();
    }

    @Override
    public PatientDetails findById(int id) {
        Optional<PatientDetails> optional = patientDetailsRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public PatientDetails save(PatientDetails patientDetails) {
        return patientDetailsRepo.save(patientDetails);
    }

    @Override
    public String deleteById(int id) {
        patientDetailsRepo.deleteById(id);
        return "Patient details deleted successfully";
    }
}
