package com.yandev.appointment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/appointment")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getAppointmentsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(this.appointmentService.getAppointmentByPatient(patientId));
    }
}
