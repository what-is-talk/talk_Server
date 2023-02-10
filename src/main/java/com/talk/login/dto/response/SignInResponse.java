package com.talk.login.dto.response;

import com.talk.enums.AuthProvider;
import com.talk.login.dto.KakaoUserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class SignInResponse {

    private AuthProvider authProvider;

    private Long id;

    private String name;


//    private KakaoUserInfo kakaoUserInfo;

    private String accessToken;

    @Builder
    public SignInResponse(AuthProvider authProvider, Long id, String name, String accessToken) {
        this.authProvider = authProvider;
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
    }

}
