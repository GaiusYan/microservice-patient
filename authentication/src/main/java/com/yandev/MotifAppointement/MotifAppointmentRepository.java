package com.yandev.MotifAppointement;

import com.yandev.appointment.Appointment;
import com.yandev.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotifAppointmentRepository extends JpaRepository<MotifAppointment, Integer> {

}
