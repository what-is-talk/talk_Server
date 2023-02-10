package com.talk.login.dto.request;

import com.talk.enums.AuthProvider;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class SignUpRequest {

    private String email;
    private String name;
    private String token;

}
