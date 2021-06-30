package com.example.securityspring.controller;

import com.example.securityspring.dto.JwtRequestDto;
import com.example.securityspring.dto.JwtResponseDto;
import com.example.securityspring.dto.MemberSignupRequestDto;
import com.example.securityspring.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponseDto login(@RequestBody JwtRequestDto request) {

        try {
            return authService.login(request);
        } catch (Exception e) {
            return new JwtResponseDto(e.getMessage());
        }
    }

    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody MemberSignupRequestDto request) {
        return authService.signup(request);
    }
}
