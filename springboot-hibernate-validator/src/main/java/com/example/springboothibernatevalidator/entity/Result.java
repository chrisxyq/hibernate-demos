package com.example.springboothibernatevalidator.entity;

import com.example.springboothibernatevalidator.util.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {

    private int    code;
    private String message;

    public static Result buildErrorResult(ErrorCodeEnum paramIllegal) {
        return new Result(paramIllegal.getCode(), paramIllegal.getMessage());
    }
}
