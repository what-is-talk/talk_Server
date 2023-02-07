package com.talk.login.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;


public class UserRequestDto {

    @Getter
    @Setter
    public static class Join {

        @NotEmpty(message = "이메일은 필수 입력값입니다.")
        private String email;

        private String password;

        private String name;

        private String imageUrl;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }

    }
    @Getter
    @Setter
    public static class Login {

        @NotEmpty(message = "이메일은 필수 입력값입니다.")
        private String email;

        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }

    }
}
