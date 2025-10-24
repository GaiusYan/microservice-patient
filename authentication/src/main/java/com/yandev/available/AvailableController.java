package com.yandev.available;


import com.yandev.doctor.Doctor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available")
public class AvailableController {

    private final AvailableService availableService;

    public AvailableController(AvailableService availableService) {
        this.availableService = availableService;
    }

    @PostMapping("/all")
    public ResponseEntity<?> createAllAvailables(@RequestBody List<Available> availables) {
        return ResponseEntity.ok(this.availableService.createAllAvailable(availables));
    }

    @PostMapping
    public ResponseEntity<?> createAvailable(@RequestBody AvailableRequest availableRequest) {
        return ResponseEntity.ok(this.availableService.createAvailable(availableRequest));
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAvailableByDoctor(@PathVariable("doctorId") Doctor doctor) {
        return ResponseEntity.ok(this.availableService.getAllAvailableByDoctor(doctor));
    }
}
