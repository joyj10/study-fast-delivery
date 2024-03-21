package org.delivery.api.comon.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User의 경우 1000번대 에러 사용
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeInterface {
    USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없음"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
