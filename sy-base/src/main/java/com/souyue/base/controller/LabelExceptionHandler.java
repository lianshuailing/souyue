package com.souyue.base.controller;

import com.souyue.common.pojo.Result;
import com.souyue.common.pojo.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author shkstart
 * @create 2019-09-23 9:58
 */
//方式1 @ControllerAdvice
@RestControllerAdvice//方式2
public class LabelExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    //方式1 @ResponseBody
    public Result error (Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
