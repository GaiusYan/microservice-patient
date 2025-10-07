package com.yandev.role;

import com.yandev.authority.Authority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence")
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authorities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;
}
