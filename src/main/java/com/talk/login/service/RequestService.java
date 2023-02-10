package com.talk.login.service;

import com.talk.login.dto.KakaoUserInfo;
import com.talk.login.dto.request.SignUpRequest;
import com.talk.login.dto.request.TokenRequest;
import com.talk.login.dto.response.SignInResponse;
import com.talk.login.dto.response.TokenResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface RequestService<T> {

    SignInResponse login(String token);

    SignInResponse join(MultipartFile multipartFile, SignUpRequest signUpRequest);

//    SignInResponse redirect(TokenRequest tokenRequest);
    TokenResponse getToken(TokenRequest tokenRequest);
    T getUserInfo(String accessToken);

}
