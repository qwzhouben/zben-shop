package com.zben.shop.member.api;

import com.zben.shop.common.ResultResponse;
import com.zben.shop.member.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @DESC: 会员注册服务
 * @AUTHOR: zhouben
 * @DATE: 2019/11/22 0022 20:18
 */
@RequestMapping("/member")
public interface UserApi {

    @PostMapping("/register")
    public ResultResponse register(@RequestBody UserEntity userEntity);
}
