package com.yandev.patient;

import com.yandev.occupation.Occupation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Patient {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence"
    )
    private Long id;
    private String name;
    private String tel;
    private String email;
    private String bloodGroup;
    private LocalDate dob;
    @ManyToOne
    private Occupation occupation;
}
