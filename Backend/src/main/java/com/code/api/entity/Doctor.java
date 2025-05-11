package com.code.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String specialization;

    private String qualification;
    private String available;
    

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PatientDetails> patientDetails;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Doctor() {}

    
    public Doctor(String name, String specialization, String qualification, String available) {
		super();
		this.name = name;
		this.specialization = specialization;
		this.qualification = qualification;
		this.available = available;
	}



    // Getters and Setters
    public int getId() { return id; }
    public void setId() { this.id=id;}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getAvailable() { return available; }
    public void setAvailable(String available) { this.available = available; }
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
    public List<PatientDetails> getPatientDetails() { return patientDetails; }
    public void setPatientDetails(List<PatientDetails> patientDetails) { this.patientDetails = patientDetails; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
