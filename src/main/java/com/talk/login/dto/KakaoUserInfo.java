package com.talk.login.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserInfo {

    private Long id;
    private KakaoAccount kakaoAccount;


    @Getter
    private static class KakaoAccount {
        private String email;
    }

}
