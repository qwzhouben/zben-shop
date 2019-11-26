package com.zben.shop.member.api;

import com.zben.shop.common.ResultResponse;
import com.zben.shop.member.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @DESC: 会员注册服务
 * @AUTHOR: zhouben
 * @DATE: 2019/11/22 0022 20:18
 */
@RequestMapping("/member")
public interface UserApi {

    /**
     * 注册
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    public ResultResponse register(@RequestBody UserEntity userEntity);

    /**
     * 登陆
     * @param userEntity
     * @return
     */
    @PostMapping("/login")
    public ResultResponse login(@RequestBody UserEntity userEntity);

    /**
     * 根据token获取用户
     * @param token
     * @return
     */
    @GetMapping("/getUser")
    public ResultResponse getUser(@RequestParam("token") String token);
}
