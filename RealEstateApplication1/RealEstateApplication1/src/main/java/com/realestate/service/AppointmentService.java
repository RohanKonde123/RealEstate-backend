package com.realestate.service;

import com.realestate.entity.Appointment;
import com.realestate.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // ✅ Constructor-based dependency injection (Best Practice)
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Appointment appointment) {
        if (appointment == null || appointment.getUser() == null || appointment.getProperty() == null) {
            throw new IllegalArgumentException("Invalid appointment details");
        }
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByUser(Long userId) {
        return Optional.ofNullable(appointmentRepository.findByUserId(userId))
                       .orElseThrow(() -> new RuntimeException("No appointments found for user ID: " + userId));
    }
}
