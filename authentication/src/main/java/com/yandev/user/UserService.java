package com.yandev.user;

import com.yandev.role.RoleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
       return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public User register(User user) {
        boolean exists = this.userRepository.existsByUsername(user.getUsername());
        if(exists)
            throw new IllegalArgumentException("Username already exists");
        return this.userRepository.save(user);
    }

    public void deleteUser(Long id) {
        boolean exists = this.userRepository.existsById(id);
        if(!exists)
            throw new IllegalArgumentException("User not found");
        this.userRepository.deleteById(id);
    }
}
