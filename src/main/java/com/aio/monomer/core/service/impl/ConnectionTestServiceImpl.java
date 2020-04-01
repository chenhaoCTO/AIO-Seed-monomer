package com.aio.monomer.core.service.impl;

import com.aio.monomer.common.util.AioBeanUtils;
import com.aio.monomer.core.mapper.ex.UserMapper;
import com.aio.monomer.core.mapper.ex.pojo.User;
import com.aio.monomer.core.service.ConnectionTestService;
import com.aio.monomer.core.vo.UserVo;
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
