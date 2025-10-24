package com.yandev.available;

import com.yandev.doctor.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
@Data
public class Available {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "available_sequence")
    @SequenceGenerator(
            name = "available_sequence",
            sequenceName = "available_sequence"
    )
    private Long id;
    private String startTime;
    private String endTime;
    private String dateAvailable;
    @ManyToOne
    private Doctor doctor;
}
