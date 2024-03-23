package org.delivery.api.comon.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Token 경우 2000번대 에러 사용
 */
@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeIfs {
    INVALID_TOKEN(400, 2000, "유효하지 않은 토"),
    EXPIRED_TOKEN(400, 2001, "만료된 토큰"),
    TOKEN_EXCEPTION(400, 2002, "알수 없는 토큰 에러"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
