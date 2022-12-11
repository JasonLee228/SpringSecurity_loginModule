package com.spring.loginmodule.dto.user.res;

import com.spring.loginmodule.domain.Role;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResUserDto {

    private Long id;
    private String email;
    private String name;
    private Role role;

}
