package com.yandev.speciality;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<Speciality> createSpecialities(List<Speciality> specialities) {
        return specialityRepository.saveAll(specialities);
    }

    public List<Speciality> getSpecialities() {
        return specialityRepository.findAll();
    }
}
