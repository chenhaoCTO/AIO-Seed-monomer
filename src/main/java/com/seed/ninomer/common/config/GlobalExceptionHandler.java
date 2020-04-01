package com.seed.ninomer.common.config;

import com.alibaba.fastjson.JSONObject;
import com.seed.ninomer.common.emun.JsonResultEnum;
import com.seed.ninomer.common.pojo.JsonResult;
import com.seed.ninomer.common.util.AioAjaxUtils;
import com.seed.ninomer.common.util.AioLogUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @description: 异常处理类
 * @author: Mr.chen
 * @date: 2020/3/27 16:26
 * @version: 1.0
 */
public class GlobalExceptionHandler {

    public static JsonResult errorHandler(HttpServletRequest request, Exception e) throws Exception {
        if (e instanceof NoHandlerFoundException) {
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return JsonResult.error(JsonResultEnum.NOT_FOUND.getCode(), "你访问的东西不存在！");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;
            String msg = "不支持["+ex.getMethod()+"]请求！";
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg);
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            String msg = "参数["+ex.getParameterName()+"]不能为空！";
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            String needName = ex.getRequiredType().getName();
            String msg = "参数["+ex.getName()+"]类型转换错误,需要的类型为："+needName.substring(needName.lastIndexOf(".")+1)+",值为："+ex.getValue();
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg);
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            Object inputValue = ex.getBindingResult().getFieldError().getRejectedValue();
            String msg = "输入的值[ "+inputValue+" ]有误,请重新输入";
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg);
        }  else {
            AioLogUtils.error(GlobalExceptionHandler.class, "服务器忙，请稍后再试！", e);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return JsonResult.error(JsonResultEnum.REQUEST_FAILD.getCode(), "服务器忙，请稍后再试！");
        }
    }

    public static String errorHandlerWeb(HttpServletRequest request, HttpServletResponse response, Exception e, HandlerExecutionChain handlerExecutionChain) throws Exception {
        if (e instanceof NoHandlerFoundException) {
            String msg = "访问的东西不存在！";
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return dealMsg(request, response, handlerExecutionChain, JsonResult.error(JsonResultEnum.NOT_FOUND.getCode(), "你访问的东西不存在！"), "error/404");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;
            String msg = "不支持["+ex.getMethod()+"]请求！";
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return dealMsg(request, response, handlerExecutionChain, JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg), "error/500");
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            String msg = "参数["+ex.getParameterName()+"]不能为空！";
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return dealMsg(request, response, handlerExecutionChain, JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg), "error/500");
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            String needName = ex.getRequiredType().getName();
            String msg = "参数["+ex.getName()+"]类型转换错误,需要的类型为："+needName.substring(needName.lastIndexOf(".")+1)+",值为："+ex.getValue();
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return dealMsg(request, response, handlerExecutionChain, JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg), "error/500");
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            Object inputValue = ex.getBindingResult().getFieldError().getRejectedValue();
            String msg = "输入的值[ "+inputValue+" ]有误,请重新输入";
            AioLogUtils.info(GlobalExceptionHandler.class, msg);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return dealMsg(request, response, handlerExecutionChain, JsonResult.error(JsonResultEnum.WRONG_REQUEST.getCode(), msg), "error/500");
        }  else {
            AioLogUtils.error(GlobalExceptionHandler.class, "服务器忙，请稍后再试！", e);
            AioLogUtils.info(GlobalExceptionHandler.class, "请求地址：" + request.getRequestURI() + "\t参数如下：");
            printParams(request);
            return dealMsg(request, response, handlerExecutionChain, JsonResult.error(JsonResultEnum.REQUEST_FAILD.getCode(), "服务器忙，请稍后再试！"), "error/500");
        }
    }


    private static String dealMsg(HttpServletRequest request, HttpServletResponse response, HandlerExecutionChain handlerExecutionChain, JsonResult result, String page) {
        // 找到了地址，但内容错误，500错误
        if (handlerExecutionChain != null) {
            Object handlerObj = handlerExecutionChain.getHandler();
            if (handlerObj != null && handlerObj instanceof HandlerMethod) {
                HandlerMethod handler = (HandlerMethod) handlerObj;
                if (handler.getBeanType().isAnnotationPresent(RestController.class) || handler.getMethodAnnotation(ResponseBody.class) != null) {
                    AioAjaxUtils.printAjax(response, JSONObject.toJSONString(result));
                    return null;
                } else {
                    return page;
                }
            }
        }
        // 未找到请求地址，404错误
        AioLogUtils.info(GlobalExceptionHandler.class, "未找到请求地址，404错误");
        return page;
    }


    private static void printParams(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            AioLogUtils.info(GlobalExceptionHandler.class, name + "\t--->\t" + request.getParameter(name));
        }
    }

}
