package com.zben.shop.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.zben.shop.mobile.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/26 10:28
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping("/index")
    public String index(@RequestParam("token") String token) {
        System.out.println("userEntity:" + JSONObject.toJSONString(getUserEntity(token)));
        return "index";
    }
}
