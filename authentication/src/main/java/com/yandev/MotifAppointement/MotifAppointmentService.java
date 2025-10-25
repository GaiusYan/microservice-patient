package com.yandev.MotifAppointement;

import com.yandev.appointment.Appointment;
import com.yandev.appointment.AppointmentRequest;
import com.yandev.motif.Motif;
import com.yandev.patient.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotifAppointmentService {

    private final MotifAppointmentRepository motifAppointmentRepository;

    public MotifAppointmentService(MotifAppointmentRepository motifAppointmentRepository) {
        this.motifAppointmentRepository = motifAppointmentRepository;
    }

    public MotifAppointment createMotifAppointment(MotifAppointment motifAppointment) {
        return motifAppointmentRepository.save(motifAppointment);
    }

    public List<MotifAppointment> getMotifAppointmentByAppointement(Appointment appointment) {
        return this.motifAppointmentRepository.findByAppointment(appointment);
    }


    public void createAllMotifAppointments(List<Motif> motifs, Appointment appointment) {
        for(Motif motif: motifs) {
            MotifAppointment motifAppointment = MotifAppointment.builder()
                    .appointment(appointment)
                    .motif(motif)
                    .patient(appointment.getPatient())
                    .build();
            this.createMotifAppointment(motifAppointment);
        }
    }



}
