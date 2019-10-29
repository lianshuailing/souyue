package com.souyue.recruit.controller;

import com.souyue.common.pojo.Result;
import com.souyue.common.pojo.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author shkstart
 * @create 2019-09-23 19:26
 */
@RestControllerAdvice
public class RecruitExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
