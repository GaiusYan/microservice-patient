package com.yandev.authentication;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {


    @GetMapping("/private")
    public String privateRoute(){
        return "private";
    }

    @GetMapping("/public")
    public String publicRoute(){
        return "public";
    }


    @PostMapping("/auth/register")
    public String registerRoute(@RequestBody RegisterRequest request){
        return "register" + request.getUsername();
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody AuthenticationRequest request){
        return "login" + request.getUsername();
    }
}
