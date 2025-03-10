package com.realestate.repository;

import com.realestate.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository // ✅ Improves clarity
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    // ✅ Fetch appointments by User ID
    List<Appointment> findByUserId(Long userId);
    
    // ✅ Fetch appointments by Property ID (if needed)
    List<Appointment> findByPropertyId(Long propertyId);

    // ✅ Fetch upcoming appointments for a user (Optional)
    @Query("SELECT a FROM Appointment a WHERE a.user.id = :userId AND a.appointmentDate > :currentDate")
    List<Appointment> findUpcomingAppointments(Long userId, LocalDateTime currentDate);
}
