package com.aio.monomer.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.spring5.view.ThymeleafView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description: thymeleaf
 * @author: Mr.chen
 * @date: 2020/3/27 17:23
 * @version: 1.0
 */
public class AioThymeleafViewClass extends ThymeleafView {

    @Value("${aio.staticServer}")
    private String staticServer;

    @Value("${aio.staticVersion}")
    private String staticVersion;

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("staticServer", staticServer);
        request.setAttribute("staticVersion", staticVersion);
//        String errorMsg = (String) request.getAttribute("errorMsg");
//        if (errorMsg == null || "".equals(errorMsg.trim())) {
//            // 处理调用其他服务的数据
//        }
        super.render(model, request, response);
    }

}
