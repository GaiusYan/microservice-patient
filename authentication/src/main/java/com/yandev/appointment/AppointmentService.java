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
                .status(appointmentRequest.getStatus())
                .build();
        Appointment appointmentSaved =  appointmentRepository.save(appointment);
        this.motifAppointmentService.createAllMotifAppointments(appointmentRequest.getMotifs(), appointmentSaved);
        return appointmentSaved;
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<AppointmentResponse> getAppointmentByPatient(Long patientId) {
        Patient patient = this.patientService.getPatientById(patientId);
        List<Appointment> appointments = appointmentRepository.findByPatient(patient);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for (Appointment appointment : appointments) {
            var motifAppointments = this.motifAppointmentService.getMotifAppointmentByAppointement(appointment);
            appointmentResponses.add(AppointmentResponse
                    .builder()
                    .id(appointment.getId())
                    .patient(appointment.getPatient())
                    .doctor(appointment.getDoctor())
                    .date(LocalDate.parse(appointment.getDateAppointment()))
                    .status(appointment.getStatus())
                    .createAt(appointment.getCreatedAt())
                            .motifs(motifAppointments.stream().map(MotifAppointment::getMotif).toList())
                    .timeSlot(TimeSlot.builder()
                            .start(appointment.getAppointmentTimeStart())
                            .end(appointment.getAppointmentTimeEnd())
                            .build())
                    .build());
        }
        return appointmentResponses;
    }

    public List<AppointmentResponse> getAppointmentByDoctor(Long doctorId) {
        Doctor doctor = this.doctorService.getDoctorById(doctorId);
        List<Appointment> appointments =  this.appointmentRepository.findByDoctor(doctor);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for (Appointment appointment : appointments) {
            var motifAppointments = this.motifAppointmentService.getMotifAppointmentByAppointement(appointment);
            appointmentResponses.add(AppointmentResponse
                    .builder()
                    .id(appointment.getId())
                    .patient(appointment.getPatient())
                    .doctor(appointment.getDoctor())
                    .date(LocalDate.parse(appointment.getDateAppointment()))
                    .status(appointment.getStatus())
                    .createAt(appointment.getCreatedAt())
                    .motifs(motifAppointments.stream().map(MotifAppointment::getMotif).toList())
                    .timeSlot(TimeSlot.builder()
                            .start(appointment.getAppointmentTimeStart())
                            .end(appointment.getAppointmentTimeEnd())
                            .build())
                    .build());
        }
        return appointmentResponses;
    }


    public List<AppointmentResponse> getAppointmentByDoctorAndStatus(Long doctorId, String status) {
        Doctor doctor = this.doctorService.getDoctorById(doctorId);
        List<Appointment> appointments =  this.appointmentRepository.findByDoctorAndStatus(doctor, status);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for (Appointment appointment : appointments) {
            var motifAppointments = this.motifAppointmentService.getMotifAppointmentByAppointement(appointment);
            appointmentResponses.add(AppointmentResponse
                    .builder()
                    .id(appointment.getId())
                    .patient(appointment.getPatient())
                    .doctor(appointment.getDoctor())
                    .date(LocalDate.parse(appointment.getDateAppointment()))
                    .status(appointment.getStatus())
                    .createAt(appointment.getCreatedAt())
                    .motifs(motifAppointments.stream().map(MotifAppointment::getMotif).toList())
                    .timeSlot(TimeSlot.builder()
                            .start(appointment.getAppointmentTimeStart())
                            .end(appointment.getAppointmentTimeEnd())
                            .build())
                    .build());
        }
        return appointmentResponses;
    }


    public List<Appointment> createAllAppointment(List<Appointment> appointments) {
        return this.appointmentRepository.saveAll(appointments);
    }

    public Appointment updateAppointment(Long id,AppointmentRequest appointmentRequest) {
        Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        appointment.setStatus(appointmentRequest.getStatus());
        return this.appointmentRepository.save(appointment);
    }
}
