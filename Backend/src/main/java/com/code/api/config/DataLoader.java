package com.code.api.config;


import com.code.api.entity.Appointment;
import com.code.api.entity.Doctor;
import com.code.api.entity.PatientDetails;
import com.code.api.entity.User;
import com.code.api.repository.AppointmentRepository;
import com.code.api.repository.DoctorRepository;
import com.code.api.repository.PatientDetailsRepository;
import com.code.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PatientDetailsRepository patientDetailsRepository;
	

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
	    createDefaultUsers();
	    createDefaultDoctors();
	    createDefaultAppointments();
	    createDefaultPatientDetails();
	}

	private void createDefaultUsers() {
	    if (userRepository.findAll().isEmpty()) {
	        User admin = new User("admin", "admin@example.com", passwordEncoder.encode("admin123"), "ADMIN");
	        User doctorUser = new User("drsmith", "drsmith@example.com", passwordEncoder.encode("doc123"), "DOCTOR");
	        User patientUser = new User("john_doe", "john@example.com", passwordEncoder.encode("pass123"), "PATIENT");
	        userRepository.saveAll(Arrays.asList(admin, doctorUser, patientUser));
	        System.out.println("✅ Default users created.");
	    }
	}

	private void createDefaultDoctors() {
	    User doctorUser = userRepository.findByUsername("drsmith");
	    if (doctorUser != null && doctorRepository.findAll().isEmpty()) {
	        Doctor doctor = new Doctor();
	        doctor.setName("Dr. John Smith");
	        doctor.setSpecialization("Cardiology");
	        doctor.setQualification("MBBS, MD");
	        doctor.setAvailable("Today");
	        doctor.setUser(doctorUser);
	        doctorRepository.save(doctor);
	        System.out.println("✅ Default doctor created.");
	    }
	}

	private void createDefaultAppointments() {
	    Doctor doctor = doctorRepository.findAll().stream().findFirst().orElse(null);
	    if (doctor != null && appointmentRepository.findAll().isEmpty()) {
	        Appointment appointment = new Appointment();
	        appointment.setAppointmentDate(LocalDate.of(2025, 5, 10));
	        appointment.setAppointmentTime(LocalTime.of(14, 0));
	        appointment.setDoctor(doctor);
	        appointmentRepository.save(appointment);
	        System.out.println("✅ Default appointment created.");
	    }
	}

	private void createDefaultPatientDetails() {
	    User patientUser = userRepository.findByUsername("john_doe");
	    Doctor doctor = doctorRepository.findAll().stream().findFirst().orElse(null);
	    if (patientUser != null && doctor != null && patientDetailsRepository.findAll().isEmpty()) {
	        PatientDetails patientDetails = new PatientDetails();
	        patientDetails.setAge(28);
	        patientDetails.setGender("Male");
	        patientDetails.setPaymentStatus("PAID");
	        patientDetails.setAppointmentDate(LocalDate.of(2025, 5, 10));
	        patientDetails.setUser(patientUser);
	        patientDetails.setDoctor(doctor);
	        patientDetailsRepository.save(patientDetails);
	        System.out.println("✅ Default patient details created.");
	    }
	}

}