package com.talk.login.client;

import com.talk.login.config.KakaoFeignConfiguration;
import com.talk.login.dto.KakaoInfo;
import com.talk.login.dto.KakaoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "kakaoClient", configuration = KakaoFeignConfiguration.class)
public interface KakaoClient {

    KakaoInfo getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    KakaoToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey, @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code, @RequestParam("grant_type") String grantType);

}
