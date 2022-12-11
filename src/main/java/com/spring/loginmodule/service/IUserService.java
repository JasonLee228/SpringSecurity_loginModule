package com.spring.loginmodule.service;

import com.spring.loginmodule.dto.user.req.ReqJoinDto;
import com.spring.loginmodule.dto.user.req.ReqLoginDto;
import com.spring.loginmodule.dto.user.res.ResUserDto;

public interface IUserService {

    public ResUserDto join(ReqJoinDto reqJoinDto);
    public ResUserDto login(ReqLoginDto loginDto);

}
