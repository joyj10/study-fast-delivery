package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result OK() {
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCode) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription("에러")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCode, Throwable tx) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription(tx.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCode, String description) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription(description)
                .build();
    }
}
