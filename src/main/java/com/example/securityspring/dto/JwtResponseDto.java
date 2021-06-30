package com.example.securityspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponseDto {
    private String accessToken;
}
