package com.talk.dto.response;

import com.talk.domain.enumpack.AuthProvider;
import lombok.Builder;
import lombok.Getter;

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
