package com.yandev.motif;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Motif {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_motif"
    )
    @SequenceGenerator(
            name = "sequence_motif",
            sequenceName = "sequence_motif"
    )
    private Long id;
    private String name;
}
