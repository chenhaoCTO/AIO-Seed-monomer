package com.aio.monomer.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 异常处理类
 * @author: Mr.chen
 * @date: 2020/3/27 17:36
 * @version: 1.0
 */
@ControllerAdvice
public class AioExceptionHandler {


    @Autowired
    private AbstractHandlerMethodMapping<?> abstractHandlerMethodMapping;

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        HandlerExecutionChain handlerExecutionChain = abstractHandlerMethodMapping.getHandler(request);
        return GlobalExceptionHandler.errorHandlerWeb(request, response, e, handlerExecutionChain);
    }
}
