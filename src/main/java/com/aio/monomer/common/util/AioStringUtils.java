package com.aio.monomer.common.util;

import org.springframework.util.StringUtils;

/**
 * @description: TODO String工具栏
 * @author: Mr.chen
 * @date: 2020/3/27 11:55
 * @version: 1.0
 */
public class AioStringUtils {

    /**
    * @description : 判断String是不是空
    * @author      : Mr.chen
    * @date        : 2020/3/27 12:12
    * @param       :
    * @return      : 
    */
    public static Boolean isEmpty(String arg){
        return StringUtils.isEmpty(arg);
    }

}
