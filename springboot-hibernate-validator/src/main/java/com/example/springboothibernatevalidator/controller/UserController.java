package com.example.springboothibernatevalidator.controller;

import com.example.springboothibernatevalidator.entity.Result;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.example.springboothibernatevalidator.entity.UpdateAction;
import com.example.springboothibernatevalidator.entity.UserAO;
import com.example.springboothibernatevalidator.util.ErrorCodeEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hibernate validator框架已经集成在 spring-boot-starter-web中
 * 1.如果使用单参数校验，controller类上必须添加@Validated注解
 */
@RestController
@RequestMapping("/user")
@Validated // 单参数校验需要加的注解
public class UserController {
    /**
     * 如果使用单参数校验，controller类上必须添加@Validated注解
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public Result deleteUser(@NotNull(message = "id不能为空") Long id) {
        return Result.buildErrorResult(ErrorCodeEnum.PARAM_LEGAL);
    }

    /**
     * 对象参数校验使用时，需要先在对象的校验属性上添加注解，
     * 然后在Controller方法的对象参数前添加@Validated 注解
     * @param userAo
     * @return
     */
    @RequestMapping("/addUser")
    public Result addUser(@Validated UserAO userAo) {
        // do something
        return Result.buildErrorResult(ErrorCodeEnum.PARAM_LEGAL);
    }
    @RequestMapping("/updateUser")
    public Result updateUser(@Validated({Default.class, UpdateAction.class}) UserAO userAo) {
        // do something
        return Result.buildErrorResult(ErrorCodeEnum.PARAM_LEGAL);
    }
}
