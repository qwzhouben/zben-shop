package com.zben.shop.mobile.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.zben.shop.common.Constant;
import com.zben.shop.common.ResultResponse;
import com.zben.shop.member.entity.UserEntity;
import com.zben.shop.mobile.feign.UserFeign;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/26 0026 11:04
 */
@Controller
public class BaseController {

    @Autowired
    private UserFeign userFeign;

    public UserEntity getUserEntity(String token) {
        UserEntity data = null;
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        ResultResponse response = userFeign.getUser(token);
        if (response != null  && Constant.HTTP_200.equals(response.getCode())) {
            LinkedHashMap<String, UserEntity> map = (LinkedHashMap<String, UserEntity>) response.getData();
            String json = JSONObject.toJSONString(map);
            data = new JSONObject().parseObject(json, UserEntity.class);
        }
        return data;
    }

    public String setError(HttpServletRequest request, String msg, String address) {
        request.setAttribute("error", msg);
        return address;
    }
}
