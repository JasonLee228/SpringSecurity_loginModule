package com.spring.loginmodule.controller;

import com.spring.loginmodule.dto.user.req.ReqJoinDto;
import com.spring.loginmodule.dto.user.req.ReqLoginDto;
import com.spring.loginmodule.dto.user.res.ResUserDto;
import com.spring.loginmodule.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    @PostMapping("/join")
    public ResUserDto join(@RequestBody ReqJoinDto body) {

        ResUserDto result = userService.join(body);

        return result;
    }

    @PostMapping("/login")
    public ResUserDto login(@RequestBody ReqLoginDto body) {

        ResUserDto result = userService.login(body);

        return result;
    }


}
