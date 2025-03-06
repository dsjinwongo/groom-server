package com.example.groom.domain.auth;


import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import com.example.groom.domain.auth.dto.KakaoTokenDto;
import com.example.groom.domain.auth.dto.KakaoTokenValidationDto;
import com.example.groom.domain.auth.userInfo.UserInfoService;
import com.example.groom.entity.domain.auth.KakaoInfo;
import com.example.groom.entity.domain.auth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final UserInfoService userInfoService;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @Value("${kakao.client_id}")
    private String clientId;
    public UserInfo getUserInfoByKakaoAccessToken(String kakaoAccessCode){
        String kakaoAccessToken = this.getKakaoToken(kakaoAccessCode).getAccessToken();
        KakaoTokenValidationDto kakaoTokenValidationDto = validateKakaoToken(kakaoAccessToken);
        UserInfo userInfo;
        try{
            userInfo = this.userInfoService.getUserInfoByKakaoId(kakaoTokenValidationDto.getId());
        }catch (CustomException e){
            KakaoInfo kakaoInfo = this.getKakaoInfo(kakaoAccessToken);
            userInfo = this.userInfoService.joinUserWithKakaoInfo(kakaoInfo);
        }
        return userInfo;
    }


    private KakaoInfo getKakaoInfo(String kakaoAccessToken){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = getHeader(kakaoAccessToken, MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<KakaoInfo> exchange = template.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, entity, KakaoInfo.class);
        if(!exchange.hasBody())throw new CustomException(ErrorCode.KAKAO_INFO_GET_REQUEST_FAILD);
        return exchange.getBody();
    }

    private KakaoTokenValidationDto validateKakaoToken(String kakaoToken){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = getHeader(kakaoToken, MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<KakaoTokenValidationDto> exchange = template.exchange("https://kapi.kakao.com/v1/user/access_token_info", HttpMethod.GET, entity, KakaoTokenValidationDto.class);
        if(!exchange.hasBody())throw new CustomException(ErrorCode.KAKAO_INFO_GET_REQUEST_FAILD);
        return exchange.getBody();
    }


    private KakaoTokenDto getKakaoToken(String kakaoCode){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = getHeader(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        ResponseEntity<KakaoTokenDto> exchange = template.exchange("https://kauth.kakao.com/oauth/token"+getTokenUriParams(kakaoCode), HttpMethod.POST, entity, KakaoTokenDto.class);
        return exchange.getBody();
    }

    private String getTokenUriParams(String code){
        return "?redirect_uri="+redirectUri+"&client_id="+clientId+"&code="+code+"&grant_type=authorization_code";
    }

    private HttpHeaders getHeader(String kakaoAccessToken, MediaType mediaType){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.setContentType(mediaType);
        headers.set("Authorization", "Bearer "+kakaoAccessToken);
        return headers;
    }
    private HttpHeaders getHeader(MediaType mediaType){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.setContentType(mediaType);
        return headers;
    }
    private HttpHeaders getAKHeader(String kakaoAccessToken, MediaType mediaType){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.setContentType(mediaType);
        headers.set("Authorization", "KakaoAK "+kakaoAccessToken);
        return headers;
    }

}
