package com.talk.login.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.annotation.Profile;

@Getter
@ToString
public class KakaoAccount {
    private Profile profile;
    private String gender;
    private String birthday;
    private String email;
}
