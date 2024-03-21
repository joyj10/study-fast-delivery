package org.delivery.api.comon.error;

public interface ErrorCodeInterface {
    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
