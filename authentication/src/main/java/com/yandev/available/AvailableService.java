package com.yandev.available;

import com.yandev.doctor.Doctor;
import com.yandev.patient.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableService {
    private final AvailableRepository availableRepository;

    public AvailableService(final AvailableRepository availableRepository) {
        this.availableRepository = availableRepository;
    }

    public Available createAvailable(Available available) {
       return this.availableRepository.save(available);
    }

    public List<Available> getAllAvailableByDoctor(Doctor doctor) {
        return this.availableRepository.findByDoctor(doctor);
    }

    public List<Available> createAllAvailable(List<Available> availables) {
        return this.availableRepository.saveAll(availables);
    }
}
