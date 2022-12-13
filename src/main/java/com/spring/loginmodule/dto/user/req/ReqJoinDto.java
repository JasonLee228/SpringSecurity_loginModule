package com.spring.loginmodule.dto.user.req;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqJoinDto {

    private String email;
    private String password;
    private String name;

}
