package com.seed.ninomer.common.pojo;

import com.seed.ninomer.common.emun.JsonResultEnum;
import com.seed.ninomer.common.util.AioStringUtils;

import java.io.Serializable;

/**
 *  @description: 自定义响应结构
 *  @author: Mr.chen
 *  @date: 2020/3/27 11:20
 *  @version: 1.0
 */
public class JsonResultT<T> implements Serializable {
    private static final long serialVersionUID = 3294711335852229414L;

    /** 响应业务状态 **/
    private Integer code;

    /** 响应消息 **/
    private String msg;

    /** 响应中的数据 **/
    private T data;


    public JsonResultT(){
    }

    public JsonResultT(Integer code, String msg, T data) {
        this.code = code;
        this.data = data;
        if(AioStringUtils.isEmpty(msg)){
            this.msg = JsonResultEnum.getMsg(code);
        }else {
            this.msg = msg;
        }
    }


    public static <T> JsonResultT ok(String msg, T data){
        return new JsonResultT(JsonResultEnum.REQUEST_SUCCESS.getCode(),msg,data);
    }

    public static <T> JsonResultT ok(T data){
        return ok(null,data);
    }

    public static JsonResultT ok(){
        return ok(null,null);
    }

    public static <T> JsonResultT error(Integer code, String msg, T data){
        return new JsonResultT(code != null ? code : JsonResultEnum.REQUEST_FAILD.getCode(),msg,data);
    }

    public static <T> JsonResultT error(String msg, T data){
        return error(null,msg,data);
    }

    public static <T> JsonResultT error(Integer code, T data){
        return error(code,null,data);
    }

    public static <T> JsonResultT error(T data){
        return error(null,null,data);
    }

    public static JsonResultT error(Integer code){
        return error(code,null,null);
    }

    public static JsonResultT error(String msg){
        return error(null,msg,null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResultT{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
