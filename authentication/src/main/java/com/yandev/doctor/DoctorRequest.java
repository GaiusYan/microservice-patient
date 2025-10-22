package com.yandev.doctor;

import com.yandev.speciality.Speciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DoctorRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String tel;
    private String title;
    private Speciality speciality;
}
