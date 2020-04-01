package com.seed.ninomer.core.mapper.ex;

import com.seed.ninomer.core.mapper.ex.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {

    @Select("select user_no as userNo ,user_name as userName ,user_phone as userPhone  from tb_user;")
    List<User> findAll();

    List<User> findAll1();
}
