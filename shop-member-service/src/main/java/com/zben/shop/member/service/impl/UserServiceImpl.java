package com.zben.shop.member.service.impl;

import com.zben.shop.common.constant.TableConstant;
import com.zben.shop.member.entity.UserEntity;
import com.zben.shop.member.mapper.UserMapper;
import com.zben.shop.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/22 0022 20:23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserEntity userEntity) {
        userMapper.save(userEntity, TableConstant.TABLE_MB_USER);
    }
}
