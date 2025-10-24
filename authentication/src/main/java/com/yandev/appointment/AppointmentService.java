package com.yandev.appointment;

import com.yandev.MotifAppointement.MotifAppointment;
import com.yandev.MotifAppointement.MotifAppointmentService;
import com.yandev.doctor.Doctor;
import com.yandev.doctor.DoctorService;
import com.yandev.motif.Motif;
import com.yandev.patient.Patient;
import com.yandev.patient.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final MotifAppointmentService motifAppointmentService;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorService doctorService, PatientService patientService, MotifAppointmentService motifAppointmentService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.motifAppointmentService = motifAppointmentService;
    }

    public Appointment createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = Appointment
                .builder()
                .diagnosis(appointmentRequest.getDiagnosis())
                .appointmentTimeStart(appointmentRequest.getTimeSlot().getStart())
                .appointmentTimeEnd(appointmentRequest.getTimeSlot().getEnd())
                .createdAt(LocalDate.now())
                .dateAppointment(appointmentRequest.getDate().toString())
                .notes(appointmentRequest.getNotes())
                .doctor(appointmentRequest.getDoctor())
                .patient(appointmentRequest.getPatient())
                .build();
        Appointment appointmentSaved =  appointmentRepository.save(appointment);
        this.motifAppointmentService.createAllMotifAppointments(appointmentRequest.getMotifs(), appointmentSaved);
        return appointmentSaved;
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentByPatient(Patient patient) {
        return this.appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> getAppointmentByDoctor(Long doctorId) {
        Doctor doctor = this.doctorService.getDoctorById(doctorId);
        return this.appointmentRepository.findByDoctor(doctor);
    }

    public List<Appointment> createAllAppointment(List<Appointment> appointments) {
        return this.appointmentRepository.saveAll(appointments);
    }
}
