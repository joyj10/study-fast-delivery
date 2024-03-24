package org.delivery.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.comon.error.ErrorCode;
import org.delivery.api.comon.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * token에 대한 도메인 로직
 */
@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token) {
        Map<String, Object> map = tokenHelperIfs.validationTokenWithThrow(token);
        Object userId = map.get("userId");
        Objects.requireNonNull(userId, () -> {throw new ApiException(ErrorCode.NULL_POINT);});
        return Long.parseLong(userId.toString());
    }
}
