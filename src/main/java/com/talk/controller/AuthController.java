package com.talk.controller;


import com.talk.dto.KakaoUserInfo;
import com.talk.dto.request.SignUpRequest;
import com.talk.dto.request.TokenRequest;
import com.talk.dto.response.SignInResponse;
import com.talk.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//code 얻어서 accessToken 받기 위한 용도, 실제로는 프론트가 accessToken 줌
    @GetMapping("/login/oauth2/code/{registrationId}")
    public String redirect(
            @PathVariable("registrationId") String registrationId
            , @RequestParam("code") String code
            , @RequestParam("state") String state) {
        return authService.redirect(
                TokenRequest.builder()
                        .registrationId(registrationId)
                        .code(code)
                        .state(state)
                        .build());
    }

//    @GetMapping("/login/oauth2/code/{registrationId}")
//    public ResponseEntity<SignInResponse> redirect(
//            @PathVariable("registrationId") String registrationId
//            , @RequestParam("code") String code
//            , @RequestParam("state") String state) {
//        return ResponseEntity.ok(
//                authService.redirect(
//                        TokenRequest.builder()
//                                .registrationId(registrationId)
//                                .code(code)
//                                .state(state)
//                                .build()));
//    }

    @PostMapping("/user/login")
    public ResponseEntity<SignInResponse> login(@RequestBody Map<String, String> tokenMap) {
        return ResponseEntity.ok(
                authService.login(tokenMap.get("token"))
        );
    }

    @PostMapping("/user/join")
    public ResponseEntity<SignInResponse> join(@RequestPart(value = "ImageUrl", required = false) MultipartFile multipartFile,
                                               @RequestPart(value = "signUpRequest") SignUpRequest signUpRequest) {
        return ResponseEntity.ok(
                authService.join(multipartFile, signUpRequest)
        );
    }

    //확인 용도
    @PostMapping("/userinfo")
    public ResponseEntity<KakaoUserInfo> info(@RequestBody Map<String, String> tokenMap) {
        return ResponseEntity.ok(
                authService.info(tokenMap.get("token"))
        );
    }

}
