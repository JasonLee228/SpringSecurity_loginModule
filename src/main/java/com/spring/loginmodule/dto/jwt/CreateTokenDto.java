package com.spring.loginmodule.dto.jwt;

import com.spring.loginmodule.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTokenDto {
    private UUID userId;
    private String email;
    private Role role;
}
