package com.yandev.available;

import com.yandev.doctor.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AvailableRequest {
    private String dateAvailable;
    private String endTime;
    private String startTime;
    private Doctor doctor;
}
