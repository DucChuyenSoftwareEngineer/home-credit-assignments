package com.example.homecredit.common;

import java.util.List;

public interface ErrorResponse extends BaseError {

    public List<String> getMessage();
}
