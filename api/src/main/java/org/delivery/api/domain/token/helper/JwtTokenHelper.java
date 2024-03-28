package org.delivery.api.domain.token.helper;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        Date expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        String jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);
        Date expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        String jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            Jws<Claims> result = parser.parseClaimsJws(token);
            return new HashMap<>(result.getBody());
        } catch (SignatureException e) {
            // 토큰이 유효하지 않을 때
            throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
        } catch (ExpiredJwtException e) {
            // 만료된 토큰
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
        } catch (Exception e) {
            // 그외 에러
            throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e);
        }
    }
}
