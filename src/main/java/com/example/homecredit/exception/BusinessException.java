package com.example.homecredit.exception;

import com.example.homecredit.common.ErrorResponse;


public class BusinessException extends BaseAbstractRuntimeException {

    public BusinessException(ErrorResponse errorResponse) {
        super(errorResponse);
    }

}
