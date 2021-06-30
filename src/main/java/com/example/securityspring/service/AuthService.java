package com.example.securityspring.service;

import com.example.securityspring.domain.Member;
import com.example.securityspring.dto.JwtRequestDto;
import com.example.securityspring.dto.JwtResponseDto;
import com.example.securityspring.dto.MemberSignupRequestDto;
import com.example.securityspring.repository.MemberRepository;
import com.example.securityspring.security.JwtTokenProvider;
import com.example.securityspring.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponseDto login(JwtRequestDto request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return createJwtToken(authentication);
    }

    private JwtResponseDto createJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);
        return new JwtResponseDto(token);
    }

    public String signup(MemberSignupRequestDto request) {
        boolean existMember = memberRepository.existsById(request.getEmail());

        if (existMember) return null;

        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);
        return member.getEmail();
    }
}
