package com.example.springboothibernatevalidator.util;

import com.example.springboothibernatevalidator.entity.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

/**
 * 参数校验失败后会抛出异常，我们只需要在全局异常处理类中捕获参数校验的失败异常，
 * 然后将错误消息添加到返回值中即可。捕获异常的方法如下所示，返回值Result是我们系统自定义的返回值类。
 * 如果缺少参数抛出的异常是MissingServletRequestParameterException，
 * 单参数校验失败后抛出的异常是ConstraintViolationException，
 * get请求的对象参数校验失败后抛出的异常是BindException，
 * post请求的对象参数校验失败后抛出的异常是MethodArgumentNotValidException
 */
@RestControllerAdvice(basePackages= {"com.example.springboothibernatevalidator"})
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    Result handleException(Throwable e, HttpServletRequest request){
        // 异常处理
        if(e instanceof MissingServletRequestParameterException){
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            String msg = MessageFormat.format("缺少参数{0}", ((MissingServletRequestParameterException) e).getParameterName());
            result.setMessage(msg);
            return result;
        }
        if(e instanceof ConstraintViolationException){
            // 单个参数校验异常
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) e).getConstraintViolations();
            if(CollectionUtils.isNotEmpty(sets)){
                StringBuilder sb = new StringBuilder();
                sets.forEach(error -> {
                    if (error instanceof FieldError) {
                        sb.append(((FieldError)error).getField()).append(":");
                    }
                    sb.append(error.getMessage()).append(";");
                });
                String msg = sb.toString();
                msg = StringUtils.substring(msg, 0, msg.length() -1);
                result.setMessage(msg);
            }
            return result;
        }
        if (e instanceof BindException){
            // get请求的对象参数校验异常
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            List<ObjectError> errors = ((BindException) e).getBindingResult().getAllErrors();
            String msg = getValidExceptionMsg(errors);
            if (StringUtils.isNotBlank(msg)){
                result.setMessage(msg);
            }
            return result;
        }
        if (e instanceof MethodArgumentNotValidException){
            // post请求的对象参数校验异常
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            String msg = getValidExceptionMsg(errors);
            if (StringUtils.isNotBlank(msg)){
                result.setMessage(msg);
            }
            return result;
        }
        return Result.buildErrorResult(ErrorCodeEnum.PARAM_LEGAL);
    }

    private String getValidExceptionMsg(List<ObjectError> errors) {
        if(CollectionUtils.isNotEmpty(errors)){
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> {
                if (error instanceof FieldError) {
                    sb.append(((FieldError)error).getField()).append(":");
                }
                sb.append(error.getDefaultMessage()).append(";");
            });
            String msg = sb.toString();
            msg = StringUtils.substring(msg, 0, msg.length() -1);
            return msg;
        }
        return null;
    }
}
