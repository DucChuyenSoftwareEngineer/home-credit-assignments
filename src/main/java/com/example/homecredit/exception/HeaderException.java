package com.example.homecredit.exception;

import com.example.homecredit.common.ErrorResponse;


public class HeaderException extends BaseAbstractRuntimeException {

    public HeaderException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
