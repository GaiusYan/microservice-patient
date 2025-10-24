package com.yandev.appointment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/appointment")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getAppointmentsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(this.appointmentService.getAppointmentByPatient(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAllAppointmentsByDoctorId(@PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(this.appointmentService.getAppointmentByDoctor(doctorId));
    }

    @PostMapping("/all")
    public ResponseEntity<?> addAppointment(@RequestBody List<Appointment> appointments) {
        return ResponseEntity.ok(this.appointmentService.createAllAppointment(appointments));
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(this.appointmentService.createAppointment(appointmentRequest));
    }
}
