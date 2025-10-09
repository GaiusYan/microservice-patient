package com.yandev.occupation;

import com.netflix.discovery.provider.Serializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OccupationService {

    private final OccupationRepository occupationRepository;

    public OccupationService(final OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }


    public List<Occupation> getAllOccupations(){
        return occupationRepository.findAll();
    }

    public Occupation createOccupation(Occupation occupation) {
        this.occupationRepository.findByName(occupation.getName()).orElseThrow(() -> new IllegalArgumentException("Occupation already exists"));
        return occupationRepository.save(occupation);
    }

    public List<Occupation> createOccupation(List<Occupation> occupations) {
        return occupationRepository.saveAll(occupations);
    }

    public Occupation updateOccupation(Occupation occupation, Long id) {
        Optional<Occupation> occupationOptional = this.occupationRepository.findById(id);
        if (occupationOptional.isPresent()) {
            Occupation occupationExisting = occupationOptional.get();
            if (Objects.nonNull(occupation.getName()) && Objects.equals(occupationExisting.getName(), occupation.getName()))
                occupationExisting.setName(occupation.getName());
            return occupationRepository.save(occupation);
        }
        throw new IllegalArgumentException("Occupation does not exist");
    }

    public void deleteOccupation(Long id) {
        boolean exists = this.occupationRepository.existsById(id);
        if (!exists)
            throw new IllegalArgumentException("Occupation does not exist");
        this.occupationRepository.deleteById(id);
    }

    public Occupation getOccupationById(Long id) {
        return this.occupationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Occupation does not exist"));
    }
}
