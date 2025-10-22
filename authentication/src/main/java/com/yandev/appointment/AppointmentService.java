package com.yandev.appointment;

import com.yandev.doctor.Doctor;
import com.yandev.doctor.DoctorService;
import com.yandev.patient.Patient;
import com.yandev.patient.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorService doctorService, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public Appointment createAppointment(final Appointment appointment) {
        appointment.setCreatedAt(LocalDate.now());
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentByPatient(Long id) {
        Patient patient = this.patientService.getPatientById(id);
        return this.appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> getAppointmentByDoctor(Doctor doctor) {
        return this.appointmentRepository.findByDoctor(doctor);
    }
}
