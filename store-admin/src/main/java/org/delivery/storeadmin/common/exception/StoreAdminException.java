package org.delivery.storeadmin.common.exception;

import lombok.Getter;
import org.delivery.storeadmin.common.error.ErrorCodeIfs;

@Getter
public class StoreAdminException extends RuntimeException implements StoreAdminExceptionIfs {
    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription;

    public StoreAdminException(ErrorCodeIfs errorCodeIfs) {
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public StoreAdminException(ErrorCodeIfs errorCodeIfs, String errorDescription) {
        super(errorDescription);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public StoreAdminException(ErrorCodeIfs errorCodeIfs, Throwable tx) {
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public StoreAdminException(ErrorCodeIfs errorCodeIfs, Throwable tx, String errorDescription) {
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }
}
