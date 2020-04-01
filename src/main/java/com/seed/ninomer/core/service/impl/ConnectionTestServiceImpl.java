package com.seed.ninomer.core.service.impl;

import com.seed.ninomer.common.util.AioBeanUtils;
import com.seed.ninomer.core.mapper.ex.UserMapper;
import com.seed.ninomer.core.mapper.ex.pojo.User;
import com.seed.ninomer.core.service.ConnectionTestService;
import com.seed.ninomer.core.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionTestServiceImpl implements ConnectionTestService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> findUsers() {
        List<User> all = userMapper.findAll();
        all.addAll(userMapper.findAll1());
        return AioBeanUtils.convertList(all, UserVo.class);
    }
}
