package com.aio.monomer.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description: 类型转换工具类
 * @author: Mr.chen
 * @date: 2020/3/27 15:07
 * @version: 1.0
 */
public class AioBeanUtils {

    /**
    * @description : 单个类型转换
    * @author      : Mr.chen
    * @date        : 2020/3/27 15:11
    * @param       :
    * @return      : T 返回类型
    */
    public static <T> T convert(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        if (target == null) {
            return null;
        }
        try {
            T newInstance = target.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }


    /**
    * @description : 集合类型转换
    * @author      : Mr.chen
    * @date        : 2020/3/27 15:12
    * @param       :
    * @return      : List<T> 返回类型
    */
    public static <T> List<T> convertList(List<?> source, Class<T> target) {
        if (source == null) {
            return null;
        }
        if (target == null) {
            return null;
        }
        if (source.size() == 0) {
            return null;
        }
        try {
            List<T> result = new ArrayList<>();
            for (Object obj : source) {
                T newInstance = target.newInstance();
                BeanUtils.copyProperties(obj, newInstance);
                result.add(newInstance);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
    * @description : 转换时处理转换后的对象
    * @author      : Mr.chen
    * @date        : 2020/3/27 15:13
    * @param       :
    * @return      : List<T> 返回类型
    */
    public static <T> List<T> convertList(List<?> source, Class<T> target, Consumer<T> consumer) {
        if (source == null) {
            return null;
        }
        if (target == null) {
            return null;
        }
        if (source.size() == 0) {
            return null;
        }
        try {
            List<T> result = new ArrayList<>();
            for (Object obj : source) {
                T newInstance = target.newInstance();
                BeanUtils.copyProperties(obj, newInstance);
                consumer.accept(newInstance);
                result.add(newInstance);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
