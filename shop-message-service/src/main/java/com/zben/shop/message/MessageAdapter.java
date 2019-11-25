package com.zben.shop.message;

import com.alibaba.fastjson.JSONObject;

/**
 * @DESC: 接收消息
 * @AUTHOR: zhouben
 * @DATE: 2019/11/25 0025 16:11
 */
public interface MessageAdapter {

    public void distribute(JSONObject jsonObject);
}
