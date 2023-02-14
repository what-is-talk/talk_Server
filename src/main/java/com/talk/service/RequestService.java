package com.talk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.talk.dto.request.SignUpRequest;
import com.talk.dto.request.TokenRequest;
import com.talk.dto.response.SignInResponse;
import com.talk.dto.response.TokenResponse;
import com.talk.dto.response.VerifyResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RequestService<T> {

    SignInResponse login(String token) throws JsonProcessingException;

    SignInResponse join(MultipartFile multipartFile, SignUpRequest signUpRequest);

    SignInResponse entry(String token) throws JsonProcessingException;

    VerifyResponse verify(String token);

//    SignInResponse redirect(TokenRequest tokenRequest);
    TokenResponse getToken(TokenRequest tokenRequest);
    T getUserInfo(String accessToken);

}
