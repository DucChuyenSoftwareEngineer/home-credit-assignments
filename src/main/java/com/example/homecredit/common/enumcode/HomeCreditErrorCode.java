package com.example.homecredit.common.enumcode;

import com.example.homecredit.common.ErrorCode;

import java.util.HashMap;
import java.util.Map;

public enum HomeCreditErrorCode implements ErrorCode {

    SUCCESS(0, "0",  "Success", "Thành công", ""),
    NOT_FOUND(404, "HTTP 404", "Not Found", "", "%s does not exist"),
    DOES_NOT_EXIST(500000, "500000", "Validation Error - does not exist", "", "%s"),
    MANDATORY_FIELD_MISSING(500002, "500002", "Validation Error mandatory field missing", "", "%s"),
    REQUEST_MISMATCH(500003, "500002", "Validation Error - request mismatch", "", "%s"),
    INCORRECT_STATE(500004, "500004", "Validation Error - incorrect state", "", "%s"),
    BUSINESS_LOGIC(500005, "500005", "Validation Error - business logic", "", "%s"),
    NOT_SUPPORTED(500006, "500006", "Validation Error - not supported", "", "%s"),
    INVALID_FIELD(500007, "500007", "Validation Error - invalid field", "", "%s"),
    DUPLICATE_FIELD(500008, "500008", "Validation Error - duplicate", "", "%s"),
    INVALID_MAX_LIMIT_ITEMS(500009, "500009", "Validation Error - max value exceeded", "", "%s");


    private static final Map<Integer, HomeCreditErrorCode> LOOKUP = new HashMap<Integer, HomeCreditErrorCode>();
    static {
        for (final HomeCreditErrorCode enumeration : HomeCreditErrorCode.values()) {
            LOOKUP.put(enumeration.getCode(), enumeration);
        }
    }

    private final Integer code;
    private final String statusCode;
    private final String message;
    private final String localMessage;
    private final String subStatusCode;

    private HomeCreditErrorCode(Integer code, String statusCode, String message, String localMessage, String subStatusCode) {
        this.code = code;
        this.message = message;
        this.localMessage = localMessage;
        this.statusCode = statusCode;
        this.subStatusCode = subStatusCode;
    }

    public Integer getCode() {
        return this.code;
    }


    public String getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSubStatusCode(Object... param) {
        return this.getMessage() + " -> " + String.format(this.subStatusCode, param);
    }

    public String getSubStatusCode() {
        return this.message;
    }

    public String getLocalMessage() {
        return this.localMessage;
    }


}
