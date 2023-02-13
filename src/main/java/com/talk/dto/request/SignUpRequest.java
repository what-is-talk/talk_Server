package com.talk.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpRequest {

    private String email;
    private String name;
    private String token;

}
