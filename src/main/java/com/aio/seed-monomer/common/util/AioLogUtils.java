package com.seed.ninomer.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 日志打印工具
 * @author: Mr.chen
 * @date: 2020/3/27 16:45
 * @version: 1.0
 */
public class AioLogUtils {

    /**
    * @description : 打印异常信息和异常类（常用），【禁止重载、重写】
    * @author      : Mr.chen
    * @date        : 2020/3/27 16:46
    * @param       :
    * @return      :
    */
    public final static void error(Class<?> clazz, String message, Throwable t) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(message, t);
    }

    /**
    * @description : 打印异常信息和异常类
    * @author      : Mr.chen
    * @date        : 2020/3/27 16:46
    * @param       :
    * @return      :
    */
    public final static void error(Class<?> clazz, Throwable t) {
        error(clazz, t.getMessage(), t);
    }

    /**
    * @description : 打印信息
    * @author      : Mr.chen
    * @date        : 2020/3/27 16:46
    * @param       : 
    * @return      : 
    */
    public final static void info(Class<?> clazz, String message) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(message);
    }
}
