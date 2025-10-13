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
    private Long id;
    private String name;
    private String tel;
    private String email;
    private String bloodGroup;
    private LocalDate dob;
    @ManyToOne
    private Occupation occupation;
}
