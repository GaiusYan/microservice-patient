package com.yandev.motif;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotifService {

    private final MotifRepository motifRepository;

    public MotifService(MotifRepository motifRepository) {
        this.motifRepository = motifRepository;
    }
    /**
     * Get all motifs
     * @return List of motifs
     */
    public List<Motif> getMotifs() {
        return motifRepository.findAll();
    }


    public List<Motif> createMotifs(List<Motif> motifs) {
        return motifRepository.saveAll(motifs);
    }
}
