package com.example.springboothibernatevalidator.entity;

import javax.validation.constraints.NotBlank;

public class Phone {
    @NotBlank
    private String operatorType;

    @NotBlank
    private String phoneNum;
}
