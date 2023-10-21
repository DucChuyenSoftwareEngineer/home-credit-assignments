package com.example.homecredit.vo.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseRestResponse implements Serializable {

    private static final long serialVersionUID = -7241172283245733555L;
    private Object data;

}
