package com.yandev.patient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPatients() {
        return ResponseEntity.ok(this.patientService.getAllPatients());
    }


    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(this.patientService.createPatient(patient));
    }
}
