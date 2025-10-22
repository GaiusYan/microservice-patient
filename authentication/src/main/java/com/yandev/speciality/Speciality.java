package com.yandev.speciality;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table
public class Speciality {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "speciality_sequence")
    @SequenceGenerator(
            name = "speciality_sequence",
            sequenceName = "speciality_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
}
