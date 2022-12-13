package com.spring.loginmodule.config;

import com.spring.loginmodule.util.TokenUtil;
import com.spring.loginmodule.util.filter.CustomUserDetailService;
import com.spring.loginmodule.util.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfig corsConfig;

    private final TokenUtil tokenUtil;

    private final CustomUserDetailService userDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .httpBasic().disable()
                .formLogin().disable()

                .authorizeHttpRequests()
                .requestMatchers("/api/user/join").permitAll()
                .requestMatchers("/api/user/login").permitAll()
                .requestMatchers("/api/user/**").hasAuthority("USER")
                .anyRequest().permitAll()
//                .antMatchers("/api/login").permitAll()
//                .antMatchers("/api/token").permitAll()
//                .antMatchers("/api/home").hasAnyAuthority("USER", "ADMIN") // hasAnyRole(hasRole) 로 설정하면 prefix때문에 동작하지 않는다.
//                .antMatchers("/api/**").hasAnyAuthority("USER", "ADMIN")// role 문제로 403, 일단 주석처리
//                .antMatchers("/api/**").hasAuthority("ADMIN")// 테스트 시 path 관리할 것
//                .anyRequest().permitAll();

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenUtil, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
