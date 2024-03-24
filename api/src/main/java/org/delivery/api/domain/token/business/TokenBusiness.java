package org.delivery.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.comon.annotation.Business;
import org.delivery.api.comon.error.ErrorCode;
import org.delivery.api.comon.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.db.BaseEntity;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * 1. user entity user id 추출
     * 2. access, refresh token 발행
     * 3. converter -> token response 로 변경
     */
    public TokenResponse issueToken(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(BaseEntity::getId)
                .map(userId -> {
                    TokenDto accessToken = tokenService.issueAccessToken(userId);
                    TokenDto refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
