package com.example.homecredit.exception;

import com.example.homecredit.common.ErrorResponse;

public abstract class BaseAbstractRuntimeException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public BaseAbstractRuntimeException(final ErrorResponse errorResponse) {
        super();
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

}
