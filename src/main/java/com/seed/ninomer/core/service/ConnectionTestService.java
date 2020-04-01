package com.seed.ninomer.core.service;

import com.seed.ninomer.core.vo.UserVo;

import java.util.List;

public interface ConnectionTestService {

    /**
    * @description : 查询所有用户
    * @author      : Mr.chen
    * @date        : 2020/3/27 13:32
    * @param       : 
    * @return      : 
    */
    List<UserVo> findUsers();

}
