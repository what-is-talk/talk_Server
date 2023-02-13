package com.talk.service;

import com.talk.dto.request.SignUpRequest;
import com.talk.dto.request.TokenRequest;
import com.talk.dto.response.SignInResponse;
import com.talk.dto.response.TokenResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RequestService<T> {

    SignInResponse login(String token);

    SignInResponse join(MultipartFile multipartFile, SignUpRequest signUpRequest);

//    SignInResponse redirect(TokenRequest tokenRequest);
    TokenResponse getToken(TokenRequest tokenRequest);
    T getUserInfo(String accessToken);

}
