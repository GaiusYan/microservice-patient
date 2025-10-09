package com.yandev.occupation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occupations")
public class OccupationController {

    private final OccupationService occupationService;

    public OccupationController(OccupationService occupationService) {
        this.occupationService = occupationService;
    }

    @GetMapping
    public ResponseEntity<List<Occupation>> getOccupations() {
        return ResponseEntity.ok(this.occupationService.getAllOccupations());
    }

    @PostMapping
    public ResponseEntity<Occupation> createOccupation(@RequestBody Occupation occupation) {
        return ResponseEntity.ok(this.occupationService.createOccupation(occupation));
    }

    @PostMapping("/all")
    public ResponseEntity<List<Occupation>> createOccupation(@RequestBody List<Occupation> occupations) {
        return ResponseEntity.ok(this.occupationService.createOccupation(occupations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Occupation> getOccupation(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.occupationService.getOccupationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Occupation> updateOccupation(@PathVariable("id") Long id, @RequestBody Occupation occupation) {
        return ResponseEntity.ok(this.occupationService.updateOccupation(occupation,id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOccupation(@PathVariable("id") Long id) {
       this.occupationService.deleteOccupation(id);
       return ResponseEntity.noContent().build();
    }
}
