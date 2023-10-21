package com.example.homecredit.common;

public class HomeCreditConstant {


    private HomeCreditConstant() {}

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static final String START_TIME = "START_TIME";
    public static final String REQUEST_ID = "REQUEST_ID";


    // Header constants
    public static final String AUTHORIZATION = "Authorization";
    public static final String X_REQUEST_ID = "X-Request-id";
    public static final String X_RESPONSE_ID = "X-Response-Id";
    public static final String X_TIMESTAMP = "X-TimeStamp";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String X_PAYER_ID = "X-Payer-id";
    public static final String X_CONVERSATION_ID = "X-Conversation-Id";
    public static final String X_COUNTRY_CODE = "X-Country-Code";
    public static final String X_CC_URL = "X-CC-Url";


    // evn
    public static final String PROFILE_DEV = "DEV";
    public static final String PROFILE_PRO = "PRO";


    // api
    public static final String API_QUOTE_PATH = "/api/quote";

    public static final String API_ID_PATH ="/{id}";
    public static final String API_RANDOM_PATH = "/random";
    public static final String API_SEARCH_PATH = "/search";

}
