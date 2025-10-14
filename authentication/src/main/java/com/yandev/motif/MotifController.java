package com.yandev.motif;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motif")
@CrossOrigin(origins = "http://localhost:3000")
public class MotifController {

    private final MotifService motifService;

    public MotifController(final MotifService motifService) {
        this.motifService = motifService;
    }

    @GetMapping
    public ResponseEntity<?> getMotifs() {
        return ResponseEntity.ok(motifService.getMotifs());
    }

    @PostMapping("/all")
    public ResponseEntity<?> createMotifs(@RequestBody List<Motif> motifs) {
        return ResponseEntity.ok(this.motifService.createMotifs(motifs));
    }
}
