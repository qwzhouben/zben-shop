package com.zben.shop.member.api;

import com.zben.shop.common.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2019/11/22 0022 12:14
 */
@RequestMapping("/demo")
public interface DemoService {

    @GetMapping("/demo")
    public ResultResponse<String> demo();

    @GetMapping("/setKey")
    public ResultResponse<String> setKey(String key, String value, Long timeOut);

    @GetMapping("/getKey")
    public ResultResponse<Object> setKey(String key);
}
