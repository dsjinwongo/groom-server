package com.example.groom.domain.auth;


import com.example.groom.common.auth.jwt.AuthenticationToken;
import com.example.groom.common.auth.jwt.JwtAuthentication;
import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import com.example.groom.domain.auth.dto.AuthenticationTokenResponseDTO;
import com.example.groom.domain.auth.dto.RefreshTokenDto;
import com.example.groom.domain.auth.userInfo.dto.UserInfoRoomDto;
import com.example.groom.entity.domain.auth.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "auth controller", description = "인증관련 엔드포인트")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "토큰 재발급",
            description = "refresh token을 클라이언트로부터 전달받아 userInfoId를 기반으로 새로은 토큰쌍을 발급합니다.",
            tags = {"인증", "토큰"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "재발급 성공",
                    content = @Content(schema = @Schema(implementation = AuthenticationTokenResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "리프레쉬토큰이 서버에 존재하지 않습니다.")
    })
    @PostMapping("/refresh")
    public AuthenticationToken refreshAuthenticationToken(
            @Parameter(description = "refresh token")
            @RequestBody RefreshTokenDto refreshTokenDto) {
        if (refreshTokenDto.getRefreshToken() == null || refreshTokenDto.getRefreshToken().isEmpty())
            throw new CustomException(ErrorCode.REFRESH_TOKEN_REQUIRED);
        return this.authService.tokenRefresh(refreshTokenDto.getRefreshToken());
    }


    @Operation(summary = "카카오 로그인/회원가입",
            description = "1. 사용자가 카카오 로그인에 성공하면 결과로 클라이언트로부터 인가 코드를 전달받습니다." +
                    "2. 전달 받은 인증 코드를 카카오 서버에 제출하여 카카오서버 JWT토큰을 발급받습니다. " +
                    "3. 해당 토큰을 카카오 서버에 제출하여 사용자 id 정보를 받습니다." +
                    "4-1-1. 해당 id 정보가 DB에 있는지 조회한 후 없다면 회원가입을 실시합니다." +
                    "4-1-2. 카카오 서버에 토큰을 제출하여 유저정보를 가져와 DB의 UserInfo.kakao에 저장합니다." +
                    "5. 발급받은 카카오서버 refreshToken accessToken을 UserInfo.kakao에 저장합니다." +
                    "6. DB에 저장된 UserInfo id값을 토큰에 포함하여 issue하고 반환합니다.",
            tags = {"인증", "로그인", "회원가입"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = AuthenticationTokenResponseDTO.class))),
            @ApiResponse(responseCode = "406", description = "카카오 정보 가져오기에 실패했습니다."),
    })
    @GetMapping("/kakao/login")
    public AuthenticationToken loginWithKakaoCode
            (@Parameter(description = "카카오 로그인에 성공하여 받아진 인가코드")
             @RequestParam("code") String kakaoCode) {
        return this.authService.login(kakaoCode);
    }

    @Operation(summary = "유저 정보 가져오기",
            description = "자기 자신의 정보를 열람합니다.",
            tags = {"인증", "본인 정보 보기"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 가져오기 성공",
                    content = @Content(schema = @Schema(implementation = UserInfo.class))),
    })
    @GetMapping("/me")
    public UserInfoRoomDto getMe(JwtAuthentication authentication) {
        return this.authService.getMe(authentication);
    }
}
