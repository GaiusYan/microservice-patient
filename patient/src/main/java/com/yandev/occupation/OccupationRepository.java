package com.yandev.occupation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends CrudRepository<Occupation, Long> {
}
