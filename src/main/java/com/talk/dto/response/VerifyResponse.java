package com.talk.dto.response;

import com.talk.domain.enumpack.AuthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyResponse {

    private AuthProvider authProvider;

    private Long id;

    private String name;

    private String accessToken;

}
