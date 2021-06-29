package com.example.securityspring.service;

import com.example.securityspring.dto.MemberSignupRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    @DisplayName("회원가입")
    void signup() {
        // Given
        MemberSignupRequestDto request = new MemberSignupRequestDto();
        request.setEmail("test@gmail.com");
        request.setPassword("123");
        request.setName("테스터");

        // When
        String email = authService.signup(request);

        // Then
        assertThat(email).isEqualTo(request.getEmail());
    }
}