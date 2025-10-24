package com.yandev.MotifAppointement;

import com.yandev.appointment.Appointment;
import com.yandev.motif.Motif;
import com.yandev.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MotifAppointment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_motif_appointment"
    )
    @SequenceGenerator(
            name = "sequence_motif_appointment",
            sequenceName = "sequence_motif_appointment"
    )
    private Long id;
    @ManyToOne
    private Motif motif;
    @ManyToOne
    private Appointment appointment;
    @ManyToOne
    private Patient patient;
}
