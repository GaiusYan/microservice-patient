package com.yandev.authentication;

import com.yandev.role.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(this.authenticationService.register(authenticationRequest));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws IllegalAccessException {
        return ResponseEntity.ok(this.authenticationService.login(loginRequest));
    }


    @PostMapping("/auh/verify")
    public ResponseEntity<?> verify(@RequestBody String token) throws IllegalAccessException {
        return ResponseEntity.ok(this.authenticationService.verify(token));
    }
}
