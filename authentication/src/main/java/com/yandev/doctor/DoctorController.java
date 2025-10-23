package com.yandev.doctor;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<?> addDoctor(@RequestBody DoctorRequest doctorRequest) {
        return ResponseEntity.ok(doctorService.createDoctor(doctorRequest));
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getDoctorByMail(@PathVariable("email") String email) {
        return ResponseEntity.ok(doctorService.getDoctorByEmail(email));
    }
    
    @GetMapping
    public ResponseEntity<?> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") Long id) throws IllegalAccessException {
        this.doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Suppression effectuée avec succès");
    }
}
