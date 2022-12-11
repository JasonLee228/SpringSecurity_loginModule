package com.spring.loginmodule.service.impl;

import com.spring.loginmodule.dao.UserRepository;
import com.spring.loginmodule.dto.user.req.ReqJoinDto;
import com.spring.loginmodule.dto.user.req.ReqLoginDto;
import com.spring.loginmodule.dto.user.res.ResUserDto;
import com.spring.loginmodule.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public ResUserDto join(ReqJoinDto reqJoinDto) {
        return null;
    }

    @Override
    public ResUserDto login(ReqLoginDto loginDto) {
        return null;
    }

}
