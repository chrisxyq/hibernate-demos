package com.example.springboothibernatevalidator.util;

import lombok.AllArgsConstructor;
import lombok.Data;

public enum ErrorCodeEnum {
    PARAM_ILLEGAL(1000,"PARAM_ILLEGAL"),
    PARAM_LEGAL(1001,"PARAM_LEGAL");
    private  String message;
    private  int code;

    private ErrorCodeEnum(int code,String message){
        this.code=code;
        this.message=message;
    }
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
