package com.talk.dto.response;

import com.talk.domain.enumpack.AuthProvider;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SignInResponse {

    private AuthProvider authProvider;

    private Long id;

    private String name;

    private String accessToken;

    private List<Object> groupList;

    @Builder
    public SignInResponse(AuthProvider authProvider, Long id, String name, String accessToken, List<Object> groupList) {
        this.authProvider = authProvider;
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
        this.groupList = groupList;
    }

}
