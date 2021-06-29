package com.example.securityspring.domain;

import com.example.securityspring.dto.MemberSignupRequestDto;
import com.example.securityspring.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(MemberSignupRequestDto request) {
        email = request.getEmail();
        password = request.getPassword();
        name = request.getName();
        role = Role.USER;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}
