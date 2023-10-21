package com.example.homecredit.advice;

import com.example.homecredit.common.HomeCreditConstant;
import com.example.homecredit.common.enumcode.HomeCreditErrorCode;
import com.example.homecredit.exception.HeaderException;
import com.example.homecredit.util.CheckUtil;
import com.example.homecredit.vo.base.BaseErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;


@Configuration
@Slf4j
public class HomeCreditHandlerInterceptor implements HandlerInterceptor {

    // rule when client send request header
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<String> errorMessage = new ArrayList<>();
        Map<String, String> headers = new HashMap<>();
        headers.put(HomeCreditConstant.X_REQUEST_ID, request.getHeader(HomeCreditConstant.X_REQUEST_ID));
        headers.put(HomeCreditConstant.X_TIMESTAMP, request.getHeader(HomeCreditConstant.X_TIMESTAMP));
        headers.put(HomeCreditConstant.ACCEPT_LANGUAGE, request.getHeader(HomeCreditConstant.ACCEPT_LANGUAGE));
        headers.put(HomeCreditConstant.X_PAYER_ID, request.getHeader(HomeCreditConstant.X_PAYER_ID));
        headers.put(HomeCreditConstant.X_CONVERSATION_ID, request.getHeader(HomeCreditConstant.X_CONVERSATION_ID));
        headers.put(HomeCreditConstant.X_COUNTRY_CODE, request.getHeader(HomeCreditConstant.X_COUNTRY_CODE));
        log.info("---------HEADER FILTER-------------");
        headers.forEach((key, value) -> log.info("Header: {} ---> {}", key, value));
        log.info("---------HEADER-------------");
        headers.forEach((key, value) -> {
            switch (key) {
                case HomeCreditConstant.X_CONVERSATION_ID:
                    response.setHeader(HomeCreditConstant.X_CONVERSATION_ID, value);
                    break;
                case HomeCreditConstant.X_REQUEST_ID:
                    response.setHeader(HomeCreditConstant.X_RESPONSE_ID, value);
                default:
                    if (CheckUtil.isNullOrEmpty(value)) {
                        errorMessage.add(String.format("%s Header -> %s", HomeCreditErrorCode.MANDATORY_FIELD_MISSING.getMessage(),key));
                    }
            }
        });
        if (errorMessage.size() != 0) {
            log.error("Validate header failed: {}", errorMessage);
            BaseErrorResponse baseErrorResponse = new BaseErrorResponse(HomeCreditErrorCode.MANDATORY_FIELD_MISSING,errorMessage);
            throw new HeaderException(baseErrorResponse);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
