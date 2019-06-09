package com.cqyc.food.comm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义异常，继承运行时异常
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class YcException extends RuntimeException {
    private ExceptionEnums exceptionEnums;
}
