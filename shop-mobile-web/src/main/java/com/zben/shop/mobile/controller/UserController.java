package com.zben.shop.mobile.controller;

import com.zben.shop.common.Constant;
import com.zben.shop.common.ResultResponse;
import com.zben.shop.common.constant.TokenConstant;
import com.zben.shop.common.utils.CookieUtil;
import com.zben.shop.member.entity.UserEntity;
import com.zben.shop.mobile.base.controller.BaseController;
import com.zben.shop.mobile.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/26 0026 11:53
 */
@Controller
public class UserController extends BaseController {

    private static final String REGISTER = "register";
    private static final String LOGIN = "login";
    private static final String INDEX = "index";

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/localRegister")
    public String localRegister() {
        return REGISTER;
    }

    @GetMapping("/localLogin")
    public String localLogin() {
        return LOGIN;
    }

    @PostMapping(value = "register", consumes="application/x-www-form-urlencoded")
    public String register(UserEntity userEntity, HttpServletRequest request) {
        try {
            ResultResponse response = userFeign.register(userEntity);
            if (response == null || !Constant.HTTP_200.equals(response.getCode())) {
                setError(request, response.getMsg(), REGISTER);
            }
        } catch (Exception e) {
            setError(request,"注册失败", REGISTER);
        }
        return "redirect:" + LOGIN;
    }

    @PostMapping("/login")
    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) {
        ResultResponse result = userFeign.login(userEntity);
        if (response == null || !Constant.HTTP_200.equals(result.getCode())) {
            setError(request, result.getMsg(), LOGIN);
        }
        // 登录成功之后,获取token.将这个token存放在cookie
        String token = (String) result.getData();
        CookieUtil.addCookie(response, TokenConstant.USER_TOKEN, token, TokenConstant.WEB_USER_TOKEN_TIMEOUT);

        return INDEX;
    }
}
