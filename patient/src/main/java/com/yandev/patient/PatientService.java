package com.yandev.patient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return this.patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return this.patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ce patient n'existe pas"));
    }

    public Patient createPatient(Patient patient) {
        Optional<Patient> patientOptional = this.patientRepository.findByName(patient.getName());
        if (patientOptional.isPresent()) {
            throw new IllegalArgumentException("Ce patient existe déjà");
        }
        return this.patientRepository.save(patient);
    }


    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> patientOptional = this.patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            Patient patientExisting = patientOptional.get();

            if (Objects.nonNull(patient.getName()) && Objects.nonNull(patientExisting.getName())) {
                patientExisting.setName(patient.getName());
            }
            this.patientRepository.save(patientExisting);
        }
        throw new IllegalArgumentException("Ce patient n'existe pas");
    }
}
