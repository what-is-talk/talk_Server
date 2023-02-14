package com.talk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.talk.domain.enumpack.AuthProvider;
import com.talk.dto.response.VerifyResponse;
import com.talk.lib.BadRequestException;
import com.talk.dto.KakaoUserInfo;
import com.talk.dto.request.SignUpRequest;
import com.talk.dto.request.TokenRequest;
import com.talk.dto.response.SignInResponse;
import com.talk.domain.MemberRepository;
import com.talk.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KakaoRequestService kakaoRequestService;
    private final MemberRepository memberRepository;
    private final SecurityUtil securityUtil;

//    public SignInResponse redirect(TokenRequest tokenRequest){
//        if(AuthProvider.KAKAO.getAuthProvider().equals(tokenRequest.getRegistrationId())) {
//            return kakaoRequestService.redirect(tokenRequest);
//        }
//        throw new BadRequestException("not supported oauth provider");
//    }

    public SignInResponse login(String token) throws JsonProcessingException {
        return kakaoRequestService.login(token);
    }

    public SignInResponse join(MultipartFile multipartFile, SignUpRequest signUpRequest) {
        return kakaoRequestService.join(multipartFile, signUpRequest);
    }

    public KakaoUserInfo info(String token) {return kakaoRequestService.getUserInfo(token); };

    public String redirect(TokenRequest tokenRequest){
        if(AuthProvider.KAKAO.getAuthProvider().equals(tokenRequest.getRegistrationId())) {
            return kakaoRequestService.getToken(tokenRequest).getAccessToken();
        }
        throw new BadRequestException("not supported oauth provider");
    }

    public SignInResponse entry(String token) throws JsonProcessingException {
        return kakaoRequestService.entry(token);
    }

    public VerifyResponse verify(String token) {
        return kakaoRequestService.verify(token);
    }

}
