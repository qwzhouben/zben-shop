package com.zben.shop.member.controller;

import com.zben.shop.common.Constant;
import com.zben.shop.common.ResultResponse;
import com.zben.shop.member.api.UserApi;
import com.zben.shop.member.entity.UserEntity;
import com.zben.shop.member.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESC: 会员注册服务 实现
 * @author: zhouben
 * @date: 2019/11/22 0022 20:21
 */
@Slf4j
@RestController
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public ResultResponse register(@RequestBody UserEntity userEntity) {
        try {
            userService.register(userEntity);
        } catch (Exception e) {
            log.error("###UserController.register error", e);
            return ResultResponse.setFail(Constant.HTTP_400, "注册失败");
        }
        return ResultResponse.setSuccess();
    }
}
