package com.code.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int age;
    private String gender;
    private LocalDate appointmentDate;
    private String paymentStatus;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore  // Optional: prevents nested doctor loops
    private Doctor doctor;

    public PatientDetails() {}

    public PatientDetails(int age, String gender, LocalDate appointmentDate, String paymentStatus,
                          User user, Doctor doctor) {
        this.age = age;
        this.gender = gender;
        this.appointmentDate = appointmentDate;
        this.paymentStatus = paymentStatus;
        this.user = user;
        this.doctor = doctor;
    }

    // Getters and Setters
    public int getId() { return id; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getAppointmentDate() { return appointmentDate; }

    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getPaymentStatus() { return paymentStatus; }

    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Doctor getDoctor() { return doctor; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
