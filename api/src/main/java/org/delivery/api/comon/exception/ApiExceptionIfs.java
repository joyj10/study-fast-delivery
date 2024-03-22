package org.delivery.api.comon.exception;

import org.delivery.api.comon.error.ErrorCodeIfs;

public interface ApiExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();
}
