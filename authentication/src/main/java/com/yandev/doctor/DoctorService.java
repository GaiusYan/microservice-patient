package com.yandev.doctor;

import com.yandev.user.User;
import com.yandev.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public Doctor createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = Doctor.builder()
                .firstName(doctorRequest.getFirstName())
                .lastName(doctorRequest.getLastName())
                .email(doctorRequest.getEmail())
                .tel(doctorRequest.getTel())
                .title(doctorRequest.getTitle())
                .speciality(doctorRequest.getSpeciality())
                .build();

        User user = User
                .builder()
                .username(doctorRequest.getUsername())
                .password(passwordEncoder.encode(doctorRequest.getPassword()))
                .build();

        Doctor doctorRegistered =  doctorRepository.save(doctor);
        user.setUserId(doctorRegistered.getId());
        this.userService.register(user);
        return doctorRegistered;
    }

     public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }


    public void deleteDoctor(Long id) throws IllegalAccessException {
        Optional<Doctor> doctorOptional = this.doctorRepository.findById(id);
        if (!doctorOptional.isPresent())
            throw new IllegalAccessException("Doctor not found");

        Doctor doctor = doctorOptional.get();
        User user = this.userService.loadUserByUsername(doctor.getEmail());
        this.userService.deleteUser(user.getId());
        this.doctorRepository.deleteById(id);

    }

    public Doctor getDoctorById(Long doctorId) {
        return this.doctorRepository
                .findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

}
