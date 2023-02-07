package com.talk.login.controller;

import com.talk.jwt.JwtTokenProvider;
import com.talk.lib.Helper;
import com.talk.login.dto.Response;
import com.talk.login.dto.request.UserRequestDto;
import com.talk.login.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UsersController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UsersService usersService;
    private final Response response;

//    @PostMapping("/sign-up")
//    public ResponseEntity<?> singUp(@Validated UserRequestDto.SignUp signUp, Errors errors) {
//        // validation check
//        if (errors.hasErrors()) {
//            return response.invalidFields(Helper.refineErrors(errors));
//        }
//        return usersService.signUp(signUp);
//    }


    @PostMapping("/join")
    public HashMap<String, Object> join(@RequestBody @Validated UserRequestDto.Join join, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.fail("validation error");
        }
        return usersService.join(join);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Validated UserRequestDto.Login login, Errors errors) {
//        // validation check
//        if (errors.hasErrors()) {
//            return response.invalidFields(Helper.refineErrors(errors));
//        }
//        return usersService.login(login);
//    }

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody @Validated UserRequestDto.Login login, Errors errors)  {
        // validation check
        if (errors.hasErrors()) {
            return response.fail("validation error");
        }
        return usersService.login(login);
    }

    @GetMapping("/authority")
    public HashMap<String, Object> authority() {
        log.info("ADD ROLE_ADMIN");
        return usersService.authority();
    }

    @GetMapping("/userTest")
    public HashMap<String, Object> userTest() {
        log.info("ROLE_USER TEST");
        return response.success(null, null, null);
    }

    @GetMapping("/adminTest")
    public HashMap<String, Object> adminTest() {
        log.info("ROLE_ADMIN TEST");
        return response.success(null, null, null);
    }

}
