package com.example.homecredit.api;

import com.example.homecredit.vo.base.BaseRestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseApiController {

    public BaseApiController() {
    }

    // a common method for returning
    protected ResponseEntity<BaseRestResponse> reply(Object result, HttpStatus httpStatus) {
        // Creating a new instance of the BaseRestResponse
        BaseRestResponse response = new BaseRestResponse();
        response.setData(result);
        return new ResponseEntity(response, httpStatus);
    }

}
