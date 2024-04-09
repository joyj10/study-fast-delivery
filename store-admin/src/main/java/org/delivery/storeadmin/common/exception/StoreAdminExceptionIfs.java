package org.delivery.storeadmin.common.exception;


import org.delivery.storeadmin.common.error.ErrorCodeIfs;

public interface StoreAdminExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();
}
