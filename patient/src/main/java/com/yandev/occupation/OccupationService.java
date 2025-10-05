package com.yandev.occupation;

import com.netflix.discovery.provider.Serializer;
import org.springframework.stereotype.Service;

@Service
public class OccupationService {

    private final OccupationRepository occupationRepository;

    public OccupationService(final OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }
}
