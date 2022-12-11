package com.spring.loginmodule.util.filter;

import com.spring.loginmodule.dao.UserRepository;
import com.spring.loginmodule.domain.User;
import com.spring.loginmodule.exception.BaseException;
import com.spring.loginmodule.util.OptionalUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final OptionalUtil optionalUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        optionalUtil.ifEmptyThrowError(optionalUser, new BaseException());

        User user = optionalUser.get();
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getRole().toString());
    }

}
