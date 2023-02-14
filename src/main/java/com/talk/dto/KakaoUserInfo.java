package com.talk.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserInfo {

    private Long id;
    private KakaoAccount kakaoAccount;

    public String getKakaoAccountEmail() {
        return kakaoAccount.email;
    }

    @Getter
    private static class KakaoAccount {
        private String email;
    }

}
