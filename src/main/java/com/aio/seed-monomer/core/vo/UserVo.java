package com.seed.ninomer.core.vo;

import java.io.Serializable;

/**
 * @description: 用户实体
 * @author: Mr.chen
 * @date: 2020/3/27 13:29
 * @version: 1.0
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = -4023518800521410303L;

    private Integer userNo;

    private String userName;

    private String userPhone;

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
