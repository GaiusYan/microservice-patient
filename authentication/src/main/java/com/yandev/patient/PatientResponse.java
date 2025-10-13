package com.yandev.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientResponse {
    private Long id;
    private String name;
    private String tel;
    private String email;
    private String bloodGroup;
    private LocalDate dob;
    private Occupation occupation;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    private static class Occupation {
        private Long id;
        private String name;
    }
}
