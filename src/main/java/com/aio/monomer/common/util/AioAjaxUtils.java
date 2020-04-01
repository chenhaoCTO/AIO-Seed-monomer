package com.aio.monomer.common.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: ajax工具类
 * @author: Mr.chen
 * @date: 2020/3/27 16:29
 * @version: 1.0
 */
public class AioAjaxUtils {

    private AioAjaxUtils() {}

    /**
    * @description : 判断是否为ajax请求
    * @author      : Mr.chen
    * @date        : 2020/3/27 16:29
    * @param       : 
    * @return      : 
    */
    public static boolean isAjax(ServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String header = httpRequest.getHeader("X-Requested-With");

        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
    * @description : 使用ajax方式输出
    * @author      : Mr.chen
    * @date        : 2020/3/27 16:30
    * @param       : 
    * @return      : 
    */
    public static void printAjax(HttpServletResponse response, String data) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        try {
            PrintWriter out = response.getWriter();
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
