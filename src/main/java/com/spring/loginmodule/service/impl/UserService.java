package com.spring.loginmodule.service.impl;

import com.spring.loginmodule.dao.UserRepository;
import com.spring.loginmodule.domain.User;
import com.spring.loginmodule.dto.user.req.ReqJoinDto;
import com.spring.loginmodule.dto.user.req.ReqLoginDto;
import com.spring.loginmodule.dto.user.res.ResUserDto;
import com.spring.loginmodule.service.IUserService;
import com.spring.loginmodule.util.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OptionalUtil<User> optionalUtil;

    @Override
    public ResUserDto join(ReqJoinDto reqJoinDto) {

        // 중복 방지를 위한 duplicate check 는 하는 게 좋습니다.
        String email = reqJoinDto.getEmail();

        // email search result == null?
        if(optionalUtil.ifEmptyReturnNull(userRepository.findByEmail(email)) != null) {
            System.out.println("exist!!");
            return null;
        }
        reqJoinDto.setPassword(passwordEncoder.encode(reqJoinDto.getPassword()));

        User user = userRepository.save(User.builder()
                        .dto(reqJoinDto)
                        .build());
        return ResUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    @Override
    public ResUserDto login(ReqLoginDto loginDto) {
        return null;
    }

}
