package com.yandev.authentication;

import com.yandev.config.BcryptPassword;
import com.yandev.config.JwtService;
import com.yandev.role.RoleService;
import com.yandev.user.User;
import com.yandev.user.UserService;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final BcryptPassword bcryptPassword;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleService roleService;

    public AuthenticationService(final UserService userService, final BcryptPassword bcryptPassword, AuthenticationManager authenticationManager, JwtService jwtService, RoleService roleService) {
        this.userService = userService;
        this.bcryptPassword = bcryptPassword;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.roleService = roleService;
    }


    public User register(AuthenticationRequest authenticationRequest) {
        User user = new User();
        user.setUsername(authenticationRequest.getUsername());
        user.setPassword(bcryptPassword.passwordEncoder().encode(authenticationRequest.getPassword()));
        user.setEnabled(false);
        user.setRole(Set.of(this.roleService.getRoleById(2L)));
        return this.userService.register(user);
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
        return registerResponse;
    }
}
