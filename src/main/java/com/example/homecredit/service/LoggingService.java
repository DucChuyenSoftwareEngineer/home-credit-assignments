package com.example.homecredit.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoggingService {

    public void logRequest(HttpServletRequest request, Object body);

    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body);
}
