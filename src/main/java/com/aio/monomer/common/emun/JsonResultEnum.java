package com.aio.monomer.common.emun;

/**
 *  @description: 统一相应状态码
 *  @author: Mr.chen
 *  @date: 2020/3/27 11:00
 */
public enum JsonResultEnum {

    REQUEST_SUCCESS(200,"请求成功"),
    WRONG_REQUEST(400,"请求失败"),
    NEED_LOGIN(401,"请登录"),
    NOT_FOUND(404,"访问的资源不存在"),
    REQUEST_FAILD(500,"服务器出现未知异常")
    ;

    private  Integer code;

    private String msg;

    JsonResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code){
        for (JsonResultEnum value : JsonResultEnum.values()) {
            if(value.getCode().equals(code)){
                return value.getMsg();
            }
        }
        return "未定义响应信息";
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
