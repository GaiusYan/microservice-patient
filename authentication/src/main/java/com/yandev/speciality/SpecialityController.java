package com.yandev.speciality;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/speciality")
@CrossOrigin(origins = "http://localhost:3000")
public class SpecialityController {

    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @PostMapping("/all")
    public ResponseEntity<?> createSpecialities(@RequestBody List<Speciality> specialities) {
        return ResponseEntity.ok(specialityService.createSpecialities(specialities));
    }

    @GetMapping
    public ResponseEntity<?> getAllSpecialities() {
        return ResponseEntity.ok(specialityService.getSpecialities());
    }
}
