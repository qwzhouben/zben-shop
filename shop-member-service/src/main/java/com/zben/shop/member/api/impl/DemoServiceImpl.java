package com.zben.shop.member.api.impl;

import com.zben.shop.common.ResultResponse;
import com.zben.shop.common.service.BaseRedisService;
import com.zben.shop.member.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/22 0022 12:17
 */
@RestController
public class DemoServiceImpl implements DemoService {

    @Autowired
    private BaseRedisService baseRedisService;

    @Override
    public ResultResponse<String> demo() {
        return ResultResponse.setSuccess();
    }

    @Override
    public ResultResponse<String> setKey(String key, String value, Long timeOut) {
        baseRedisService.set(key, value, 60l);
        return ResultResponse.setSuccess();
    }

    @Override
    public ResultResponse<Object> setKey(String key) {
        return ResultResponse.setSuccess(baseRedisService.getKey(key));
    }
}
