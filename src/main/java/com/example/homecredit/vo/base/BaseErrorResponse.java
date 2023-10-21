package com.example.homecredit.vo.base;


import com.example.homecredit.common.ErrorCode;
import com.example.homecredit.common.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseErrorResponse implements ErrorResponse {

    private String statusCode;
    private List<String> message;

    public BaseErrorResponse(ErrorCode errorCode) {
        this.statusCode = errorCode.getStatusCode();
        this.message.add(errorCode.getMessage());
    }

    public BaseErrorResponse(ErrorCode errorCode, List<String> message) {
        this.statusCode = errorCode.getStatusCode();
        this.message = message;
    }


    @Override
    public String getStatusCode() {
        return this.statusCode;
    }

    @Override
    public List<String> getMessage() {
        return this.message;
    }
}
