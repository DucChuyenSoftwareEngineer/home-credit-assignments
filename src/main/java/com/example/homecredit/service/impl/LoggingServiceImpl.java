package com.example.homecredit.service.impl;

import com.example.homecredit.common.HomeCreditConstant;
import com.example.homecredit.service.LoggingService;
import com.example.homecredit.util.CheckUtil;
import com.example.homecredit.util.DateUtil;
import com.example.homecredit.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoggingServiceImpl implements LoggingService {

    private final Logger logger;

    {
        logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public void logRequest(HttpServletRequest request, Object body) {
        Object startTime;
        StringBuilder log;

        log = new StringBuilder();
        startTime = request.getAttribute(HomeCreditConstant.START_TIME);

        log.append("\n---------------------------Receive request---------------------------\n");
        settingClientInfo(log, request);
        if (!CheckUtil.isNullOrEmpty(startTime)) {
            log.append(String.format("Start time: %s\n", DateUtil.format((Date) startTime, HomeCreditConstant.DATE_TIME_FORMAT)));
        }
        if (!CheckUtil.isNullOrEmpty(body)) {
            log.append(String.format("Request: %s = %s\n", body.getClass().getSimpleName(), ObjectUtil.serializable(body)));
        }
        if (!CheckUtil.isNullOrEmpty(request.getQueryString())) {
            log.append(String.format("Query: %s\n", request.getQueryString()));
        }

        log.append("---------------------------------------------------------------------");

        logger.info(log.toString());
    }

    @Override
    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {

        Object startTime;
        Date endTime;
        StringBuilder log;

        log = new StringBuilder();
        startTime = request.getAttribute(HomeCreditConstant.START_TIME);

        log.append("\n---------------------------Send response---------------------------\n");
        settingClientInfo(log, request);
        if (!CheckUtil.isNullOrEmpty(startTime)) {
            endTime = DateUtil.getSystemTime();

            log.append(String.format("End time: %s\n", DateUtil.format(endTime, HomeCreditConstant.DATE_TIME_FORMAT)));
            log.append(String.format("Process time: %s ms\n", endTime.getTime() - ((Date) startTime).getTime()));
        }
        if (!CheckUtil.isNullOrEmpty(body)) {
            if (body instanceof String) {
                log.append(String.format("Response: %s", body));
            } else {
                log.append(String.format("Response: %s = %s", body.getClass().getSimpleName(), ObjectUtil.serializable(body)));
            }
        }

        log.append("\n---------------------------------------------------------------------");

        logger.info(log.toString());
    }


    private void settingClientInfo(StringBuilder log, HttpServletRequest request) {
        String requestId;

        requestId = (String) request.getAttribute(HomeCreditConstant.REQUEST_ID);

        log.append(String.format("Remote address: %s\n", request.getRemoteAddr()));
        log.append(String.format("Url: %s\n", request.getRequestURI()));
        log.append(String.format("Method: %s\n", request.getMethod()));
        if (!CheckUtil.isNullOrEmpty(requestId)) {
            log.append(String.format("Request id: %s\n", requestId));
        }
    }
}
