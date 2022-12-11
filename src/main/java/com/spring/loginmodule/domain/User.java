package com.spring.loginmodule.domain;

import com.spring.loginmodule.dto.user.req.ReqJoinDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private Role role;

    @Builder
    public User(ReqJoinDto dto) {

        this.email = dto.getEmail();
        this.name = dto.getName();
        this.password = dto.getPassword();
        this.role = Role.ROLE_USER;

    }
}
