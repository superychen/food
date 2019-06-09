package com.cqyc.food.comm.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一解决异常处理类
 */
@ControllerAdvice
public class CommException {

    @ExceptionHandler(YcException.class)
    @ResponseBody
    public ResponseEntity<String> handleException(YcException e){
        ExceptionEnums em = e.getExceptionEnums();
        System.out.println("--------执行到这个获取异常的方法--------");
        return ResponseEntity.status(em.getCode()).body(em.getMsg());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<String> handleAuthenticationException(Exception e){
        System.out.println("--------获取登录时异常--------");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
