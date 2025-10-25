package com.yandev.authentication;

import com.yandev.config.BcryptPassword;
import com.yandev.config.JwtService;
import com.yandev.config.RestTemplateConfig;
import com.yandev.patient.Patient;
import com.yandev.patient.PatientResponse;
import com.yandev.patient.PatientService;
import com.yandev.role.RoleService;
import com.yandev.user.User;
import com.yandev.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class AuthenticationService {

    private final UserService userService;
    private final BcryptPassword bcryptPassword;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleService roleService;
    private final PatientService patientService;

    public AuthenticationService(final UserService userService, final BcryptPassword bcryptPassword, AuthenticationManager authenticationManager, JwtService jwtService, RoleService roleService,PatientService patientService) {
        this.userService = userService;
        this.bcryptPassword = bcryptPassword;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.roleService = roleService;
        this.patientService = patientService;
    }


    public User register(AuthenticationRequest authenticationRequest) {
        User user = new User();
        user.setUsername(authenticationRequest.getUsername());
        user.setPassword(bcryptPassword.passwordEncoder().encode(authenticationRequest.getPassword()));
        user.setEnabled(false);
        user.setRole(Set.of(this.roleService.getRoleById(2L)));
        User existingUser = this.userService.register(user);

        this.patientService.createPatient(Patient
                .builder()
                        .id(user.getId())
                .name(authenticationRequest.getName())
                .email(authenticationRequest.getEmail())
                .tel(authenticationRequest.getTel())
                .dob(authenticationRequest.getDob())
                .bloodGroup("0+")
                .build());
        return user;
    }


    public AuthenticationResponse login(LoginRequest loginRequest) throws IllegalAccessException {
        var user = this.userService.loadUserByUsername(loginRequest.getUsername());
        AuthenticationResponse registerResponse = new AuthenticationResponse();
        if (!user.isEnabled())
            throw new IllegalAccessException("User is not enabled");

        final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(), user.getAuthorities())
        );

        if (!authentication.isAuthenticated()){
            throw new IllegalArgumentException("Invalid credentials");
        }
        Map<String, String> token  = this.jwtService.generateJwt(loginRequest.getUsername());
        registerResponse.setToken(token.get("Bearer"));
        registerResponse.setUser(user);
        return registerResponse;
    }


    public boolean verify(String token) {
        return this.jwtService.isTokenExpired(token);
    }
}
