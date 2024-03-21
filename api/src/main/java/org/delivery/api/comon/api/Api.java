package org.delivery.api.comon.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.comon.error.ErrorCodeInterface;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data) {
        Api<T> api = new Api<>();
        api.result = Result.OK();
        api.body = data;
        return api;
    }

    public static Api<Object> ERROR(Result result) {
        Api<Object> api = new Api<>();
        api.result = result;
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeInterface errorCode) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCode);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeInterface errorCode, Throwable tx) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCode, tx);
        return api;
    }


    public static Api<Object> ERROR(ErrorCodeInterface errorCode, String description) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCode, description);
        return api;
    }
}
