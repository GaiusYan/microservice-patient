package com.yandev.available;

import com.yandev.doctor.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Available {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "available_sequence")
    @SequenceGenerator(
            name = "available_sequence",
            sequenceName = "available_sequence"
    )
    private Long id;
    private LocalTime start;
    private LocalTime end;
    private LocalDate date;
    @ManyToOne
    private Doctor doctor;
}
