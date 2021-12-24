package com.example.springboothibernatevalidator.entity;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserAO {
    /**
     * 只在UpdateAction分组下校验id字段，在默认情况下就会校验name字段和age字段。
     */
    @NotNull(groups = UpdateAction.class, message = "id不能为空")
    private Long   id;
    @NotBlank
    private String name;

    @NotNull
    private Integer age;
    @Valid
    private Phone phone;
}
