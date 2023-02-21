package com.talk.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talk.domain.Member;
import com.talk.domain.MemberMeeting;
import com.talk.domain.MemberMeetingRepository;
import com.talk.domain.enumpack.AuthProvider;
//import com.talk.login.controller.UsersController;
import com.talk.domain.enumpack.Role;
import com.talk.dto.KakaoUserInfo;
import com.talk.dto.request.SignUpRequest;
import com.talk.dto.request.TokenRequest;
import com.talk.dto.response.SignInResponse;
import com.talk.dto.response.TokenResponse;
import com.talk.domain.MemberRepository;
import com.talk.dto.response.VerifyResponse;
import com.talk.lib.BadRequestException;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class KakaoRequestService implements RequestService {
    private final MemberRepository memberRepository;
    private final SecurityUtil securityUtil;
    private final WebClient webClient;

    private final MemberMeetingRepository memberMeetingRepository;


    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String GRANT_TYPE;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.token_uri}")
    private String TOKEN_URI;

    @Override
    public SignInResponse login(String token) throws JsonProcessingException {
        KakaoUserInfo kakaoUserInfo = getUserInfo(token);

        if (memberRepository.existsById(kakaoUserInfo.getId())) {
            Member member = memberRepository.findById(kakaoUserInfo.getId()).get();
            String accessToken = securityUtil.createAccessToken(
                    String.valueOf(kakaoUserInfo.getId()), AuthProvider.KAKAO, token);

            Integer memberCount;
            List<Object> groupList = new ArrayList<>();
            List<MemberMeeting> memberMeetings = memberMeetingRepository.findByMemberId(member.getId());

            for (MemberMeeting el : memberMeetings) {
                List<MemberMeeting> memberMeetingList = memberMeetingRepository.findByMeetingId(el.getMeeting().getId());
                memberCount = memberMeetingList.size();
                Map<String, Object> map = new HashMap<>();
                map.put("id", el.getMeeting().getId());
                map.put("name", el.getMeeting().getName());
                map.put("profileImage", el.getMeeting().getGroupImageUrl());
                map.put("memberCount", memberCount);

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(map);

                groupList.add(json);

            }
            return SignInResponse.builder()
                    .authProvider(member.getAuthProvider())
                    .name(member.getName())
                    .id(member.getId())
                    .accessToken(accessToken)
                    .groupList(groupList)
                    .build();

        } else {
            return SignInResponse.builder()
                    .authProvider(AuthProvider.KAKAO)
                    .name(null)
                    .id(null)
                    .accessToken(null)
                    .groupList(null)
                    .build();

        }
    }

    public SignInResponse join(MultipartFile multipartFile, String name, String token) {

        KakaoUserInfo kakaoUserInfo = getUserInfo(token);


        if (memberRepository.existsById(kakaoUserInfo.getId())) {
            return SignInResponse.builder()
                    .authProvider(AuthProvider.KAKAO)
                    .name(null)
                    .id(null)
                    .accessToken(null)
                    .build();
        } else {
            String imageUrl = multipartFile.getOriginalFilename();

            Member member = Member.builder().id(kakaoUserInfo.getId()).email(kakaoUserInfo.getKakaoAccountEmail())
                    .name(name).profileImage(imageUrl)
                    .role(Role.USER).authProvider(AuthProvider.KAKAO).build();
            memberRepository.save(member);

            String accessToken = securityUtil.createAccessToken(
                    String.valueOf(kakaoUserInfo.getId()), AuthProvider.KAKAO, token);

            return SignInResponse.builder()
                    .authProvider(member.getAuthProvider())
                    .name(member.getName())
                    .id(member.getId())
                    .accessToken(accessToken)
                    .build();
        }

    }

    @Override
    public SignInResponse entry(String token) throws JsonProcessingException {
        if (securityUtil.isExpiration(token)) {
            throw new BadRequestException("EXPIRED_ACCESS_TOKEN");
        }

        Long memberId;
        String userId;
        Integer memberCount;
        List<Object> groupList = new ArrayList<>();

        userId = (String) securityUtil.get(token).get("userId");
        memberId = Long.parseLong(userId);

        Member member = memberRepository.findById(memberId).get();
        List<MemberMeeting> memberMeetings = memberMeetingRepository.findByMemberId(member.getId());

        for (MemberMeeting el : memberMeetings) {
            List<MemberMeeting> memberMeetingList = memberMeetingRepository.findByMeetingId(el.getMeeting().getId());
            memberCount = memberMeetingList.size();
            Map<String, Object> map = new HashMap<>();
            map.put("id", el.getMeeting().getId());
            map.put("name", el.getMeeting().getName());
            map.put("profileImage", el.getMeeting().getGroupImageUrl());
            map.put("memberCount", memberCount);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(map);

            groupList.add(json);
        }

        return SignInResponse.builder()
                .authProvider(member.getAuthProvider())
                .name(member.getName())
                .id(member.getId())
                .accessToken(token)
                .groupList(groupList)
                .build();

    }

    @Override
    public VerifyResponse verify(String token) {
        if (securityUtil.isExpiration(token)) {
            throw new BadRequestException("EXPIRED_ACCESS_TOKEN");
        }

        Long memberId;
        String userId;

        userId = (String) securityUtil.get(token).get("userId");
        memberId = Long.parseLong(userId);

        Member member = memberRepository.findById(memberId).get();

        return VerifyResponse.builder().authProvider(member.getAuthProvider()).name(member.getName())
                .id(member.getId()).accessToken(token).build();
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
