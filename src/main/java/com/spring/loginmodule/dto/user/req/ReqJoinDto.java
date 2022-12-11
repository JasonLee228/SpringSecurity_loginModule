package com.spring.loginmodule.dto.user.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReqJoinDto {

    private String email;
    private String password;
    private String name;

}
