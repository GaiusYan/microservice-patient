package com.yandev.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/register")
    public ResponseEntity<?> register() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().body("login is done");
    }
}
