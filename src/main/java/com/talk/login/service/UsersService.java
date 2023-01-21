package com.talk.login.service;

import com.talk.entity.Users;
import com.talk.enums.Authority;
import com.talk.jwt.JwtTokenProvider;
import com.talk.login.dto.Response;
import com.talk.login.dto.request.UserRequestDto;
import com.talk.login.dto.response.UserResponseDto;
import com.talk.login.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp) {
        if (usersRepository.existsByEmail(signUp.getEmail())) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        Users user = Users.builder().email(signUp.getEmail()).password(passwordEncoder.encode(signUp.getPassword()))
                .roles(Collections.singletonList(Authority.ROLE_USER.name())).build();
        usersRepository.save(user);

        return response.success("회원가입에 성공했습니다.");
    }

    public ResponseEntity<?> login(UserRequestDto.Login login) {

        if (usersRepository.findByEmail(login.getEmail()).orElse(null) == null) {
            return response.fail("해당하는 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // Login email/password 기반으로 Authentication 객체 생성
        // 이 때 authentication은 인증 여부 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();

        // 실제 검증이 이루어지는 부분
        // authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보 기반으로 JWT 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);
    }
}
