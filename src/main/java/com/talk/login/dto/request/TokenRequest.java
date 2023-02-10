package com.talk.login.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenRequest {

    private String registrationId;
    private String code;
    private String state;

    @Builder
    public TokenRequest(String registrationId, String code, String state){
        this.registrationId = registrationId;
        this.code = code;
        this.state = state;
    }

}
