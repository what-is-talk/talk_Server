package com.talk.login.service;

import com.talk.entity.Users;
import com.talk.enums.AuthProvider;
//import com.talk.login.controller.UsersController;
import com.talk.enums.Role;
import com.talk.lib.BadRequestException;
import com.talk.login.controller.AuthController;
import com.talk.login.dto.KakaoUserInfo;
import com.talk.login.dto.request.SignUpRequest;
import com.talk.login.dto.request.TokenRequest;
import com.talk.login.dto.response.SignInResponse;
import com.talk.login.dto.response.TokenResponse;
import com.talk.login.repository.UsersRepository;
import com.talk.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class KakaoRequestService implements RequestService {
    private final UsersRepository usersRepository;
    private final SecurityUtil securityUtil;
    private final WebClient webClient;


    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String GRANT_TYPE;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.token_uri}")
    private String TOKEN_URI;

    @Override
    public SignInResponse login(String token) {
        KakaoUserInfo kakaoUserInfo = getUserInfo(token);

        if (usersRepository.existsById(kakaoUserInfo.getId())) {
            Users member = usersRepository.findById(kakaoUserInfo.getId()).get();
            String accessToken = securityUtil.createAccessToken(
                    String.valueOf(kakaoUserInfo.getId()), AuthProvider.KAKAO, token);
            return SignInResponse.builder()
                    .authProvider(member.getAuthProvider())
                    .name(member.getName())
                    .id(member.getId())
                    .accessToken(accessToken)
                    .build();
        } else {
            return SignInResponse.builder()
                    .authProvider(AuthProvider.KAKAO)
                    .name(null)
                    .id(null)
                    .accessToken(null)
                    .build();

        }


    }

    public SignInResponse join(MultipartFile multipartFile, SignUpRequest signUpRequest) {

        if (usersRepository.findByEmail(signUpRequest.getEmail()).orElse(null) != null) {
            return SignInResponse.builder()
                    .authProvider(AuthProvider.KAKAO)
                    .name(null)
                    .id(null)
                    .accessToken(null)
                    .build();
        } else {
            KakaoUserInfo kakaoUserInfo = getUserInfo(signUpRequest.getToken());

            String imageUrl = multipartFile.getOriginalFilename();

            Users member = Users.builder().id(kakaoUserInfo.getId()).email(signUpRequest.getEmail())
                    .name(signUpRequest.getName()).profile_image(imageUrl)
                    .role(Role.USER).authProvider(AuthProvider.KAKAO).build();
            usersRepository.save(member);

            String accessToken = securityUtil.createAccessToken(
                    String.valueOf(kakaoUserInfo.getId()), AuthProvider.KAKAO, signUpRequest.getToken());

            return SignInResponse.builder()
                    .authProvider(member.getAuthProvider())
                    .name(member.getName())
                    .id(member.getId())
                    .accessToken(accessToken)
                    .build();
        }

    }



//    @Override
//    public SignInResponse redirect(TokenRequest tokenRequest) {
//        TokenResponse tokenResponse = getToken(tokenRequest);
//        KakaoUserInfo kakaoUserInfo = getUserInfo(tokenResponse.getAccessToken());
//
//        if(usersRepository.existsById(kakaoUserInfo.getId())) {
//            String accessToken = securityUtil.createAccessToken(
//                    String.valueOf(kakaoUserInfo.getId()), AuthProvider.KAKAO, tokenResponse.getAccessToken());
//
//            Users member = usersRepository.findById(kakaoUserInfo.getId()).get();
//
//            return SignInResponse.builder()
//                    .authProvider(AuthProvider.KAKAO)
//                    .name(member.getName())
//                    .kakaoUserInfo(kakaoUserInfo)
//                    .accessToken(accessToken)
//                    .build();
//        } else {
//            return SignInResponse.builder()
//                    .authProvider(AuthProvider.KAKAO)
//                    .name(null)
//                    .kakaoUserInfo(kakaoUserInfo)
//                    .accessToken(null)
//                    .build();
//        }
//    }

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", GRANT_TYPE);
        formData.add("redirect_uri", REDIRECT_URI);
        formData.add("client_id", CLIENT_ID);
        formData.add("code", tokenRequest.getCode());

        return webClient.mutate()
                .baseUrl(TOKEN_URI)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();
    }

    @Override
    public KakaoUserInfo getUserInfo(String accessToken) {
        return webClient.mutate()
                .baseUrl("https://kapi.kakao.com")
                .build()
                .get()
                .uri("/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserInfo.class)
                .block();
    }


}
