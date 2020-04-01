package com.seed.ninomer.common.pojo;

import com.seed.ninomer.common.emun.JsonResultEnum;
import com.seed.ninomer.common.util.AioStringUtils;

import java.io.Serializable;

/**
 *  @description: 自定义响应结构
 *  @author: Mr.chen
 *  @date: 2020/3/27 11:21
 *  @version: 1.0
 */
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 6143246603018827510L;

    /** 响应业务状态 **/
    private Integer code;

    /** 响应消息 **/
    private String msg;

    /** 响应的数据 **/
    private Object data;

    public JsonResult() {

    }

    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.data = data;

        if(AioStringUtils.isEmpty(msg)){
            this.msg = JsonResultEnum.getMsg(code);
        }else {
            this.msg = msg;
        }
    }

    public static JsonResult ok(String msg, Object data){
        return new JsonResult(JsonResultEnum.REQUEST_SUCCESS.getCode(),msg,data);
    }

    public static JsonResult ok(Object data){
        return ok(null,data);
    }

    public static JsonResult ok(){
        return ok(null,null);
    }

    public static JsonResult error(Integer code, String msg, Object data){
        return new JsonResult(code != null ? code : JsonResultEnum.REQUEST_FAILD.getCode(),msg,data);
    }

    public static JsonResult error(String msg, Object data){
        return error(null,msg,data);
    }

    public static JsonResult error(Integer code, Object data){
        return error(code,null,data);
    }

    public static JsonResult error(Object data){
        return error(null,null,data);
    }

    public static JsonResult error(Integer code){
        return error(code,null,null);
    }

    public static JsonResult error(String msg){
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
