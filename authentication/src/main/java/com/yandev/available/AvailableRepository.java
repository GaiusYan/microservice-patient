package com.yandev.available;

import com.yandev.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableRepository extends JpaRepository<Available, Long> {
    List<Available> findByDoctor(Doctor doctor);
}
