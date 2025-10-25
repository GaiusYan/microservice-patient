package com.yandev.appointment;

import com.yandev.doctor.Doctor;
import com.yandev.motif.Motif;
import com.yandev.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppointmentResponse {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private TimeSlot timeSlot;
    private List<Motif> motifs;
    private String diagnosis;
    private String status;
    private LocalDate createAt;
    private String notes;
}
