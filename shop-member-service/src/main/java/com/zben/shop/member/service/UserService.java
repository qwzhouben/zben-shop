package com.zben.shop.member.service;

import com.zben.shop.common.ResultResponse;
import com.zben.shop.member.entity.UserEntity;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2019/11/22 0022 20:21
 */
public interface UserService {

    //会员注册
    public void register(UserEntity userEntity);

    //登陆
    public ResultResponse login(UserEntity userEntity);

    //根据token获取用户
    ResultResponse getUser(String token);
}
