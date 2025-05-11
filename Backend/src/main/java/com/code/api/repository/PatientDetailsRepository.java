package com.code.api.repository;

import com.code.api.entity.PatientDetails;
import com.code.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Integer> {
    PatientDetails findByUser(User user);
}
