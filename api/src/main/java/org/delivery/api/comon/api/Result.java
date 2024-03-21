package org.delivery.api.comon.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.comon.error.ErrorCode;
import org.delivery.api.comon.error.ErrorCodeInterface;

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

    public static Result ERROR(ErrorCodeInterface errorCode) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription("에러")
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCode, Throwable tx) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription(tx.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCode, String description) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription(description)
                .build();
    }
}
