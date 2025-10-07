package com.yandev.authority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Authority {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authority_sequence")
    @SequenceGenerator(
            name = "authority_sequence",
            sequenceName = "authority_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
}
